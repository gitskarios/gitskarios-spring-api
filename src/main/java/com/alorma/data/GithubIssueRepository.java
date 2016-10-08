package com.alorma.data;

import com.alorma.data.exception.UnauthorizedException;
import com.alorma.data.github.IssueService;
import com.alorma.domain.*;
import com.alorma.domain.response.IssueResponse;
import com.alorma.domain.response.IssueStory;
import retrofit2.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class GithubIssueRepository implements IssueRepository {

    private final IssueService issueService;

    public GithubIssueRepository(IssueService issueService) {
        this.issueService = issueService;
    }

    @Override
    public IssueResponse getIssue(String owner, String repo, Integer number, String token) throws Exception {
        token = "token " + token;
        Response<Issue> issueResponse = issueService.getIssue(owner, repo, number, token).execute();

        if (!issueResponse.isSuccessful()) {
            switch (issueResponse.code()) {
                case 401:
                    throw new UnauthorizedException(issueResponse.errorBody().string());
                default:
                    throw new Exception(issueResponse.errorBody().string());
            }
        } else {
            Issue issue = issueResponse.body();
            List<GithubReaction> reactions = getAllReactions(owner, repo, number, token);

            Map<String, List<User>> reactionsMap = new HashMap<>();
            reactions.forEach(githubReaction -> {
                if (reactionsMap.get(githubReaction.getContent()) == null) {
                    reactionsMap.put(githubReaction.getContent(), new ArrayList<>());
                }

                reactionsMap.get(githubReaction.getContent()).add(githubReaction.getUser());
            });

            issue.setReactions(reactionsMap);

            List<GithubComment> comments = getAllComments(owner, repo, number, token);
            List<IssueEvent> events = getAllEvents(owner, repo, number, token);

            List<GithubIssueStoryItem> items = new ArrayList<>();
            items.addAll(comments);
            items.addAll(events);

            Collections.sort(items, (o1, o2) -> {
                if (o1.getTime() > o2.getTime()) {
                    return 1;
                } else if (o1.getTime() > o2.getTime()) {
                    return 0;
                } else {
                    return -1;
                }
            });

            return new IssueResponse(issue, new IssueStory(items));
        }
    }

    private List<GithubComment> getAllComments(String owner, String repo, Integer number, String token) throws IOException {
        Response<List<GithubComment>> response = getComments(owner, repo, number, token, null);
        Integer pageFromResponse = getPageFromResponse(response);

        List<GithubComment> accumulate = new ArrayList<>();
        accumulate.addAll(response.body());
        while (pageFromResponse != null) {
            response = getComments(owner, repo, number, token, pageFromResponse);
            accumulate.addAll(response.body());
            pageFromResponse = getPageFromResponse(response);
        }
        return accumulate;
    }

    private Response<List<GithubComment>> getComments(String owner, String repo, Integer number, String token, Integer page) throws IOException {
        return issueService.comments(owner, repo, number, token, page).execute();
    }

    private List<IssueEvent> getAllEvents(String owner, String repo, Integer number, String token) throws IOException {
        Response<List<IssueEvent>> response = getEvents(owner, repo, number, token, null);
        Integer pageFromResponse = getPageFromResponse(response);

        List<IssueEvent> accumulate = new ArrayList<>();
        accumulate.addAll(response.body());
        while (pageFromResponse != null) {
            response = getEvents(owner, repo, number, token, pageFromResponse);
            accumulate.addAll(response.body());
            pageFromResponse = getPageFromResponse(response);
        }
        return accumulate;
    }

    private Response<List<IssueEvent>> getEvents(String owner, String repo, Integer number, String token, Integer page) throws IOException {
        return issueService.events(owner, repo, number, token, page).execute();
    }

    private List<GithubReaction> getAllReactions(String owner, String repo, Integer number, String token) throws IOException {
        Response<List<GithubReaction>> response = getReactions(owner, repo, number, token, null);
        Integer pageFromResponse = getPageFromResponse(response);

        List<GithubReaction> accumulate = new ArrayList<>();
        if (response.body() != null) {
            accumulate.addAll(response.body());
        }
        while (pageFromResponse != null) {
            response = getReactions(owner, repo, number, token, pageFromResponse);
            if (response.body() != null) {
                accumulate.addAll(response.body());
            }
            pageFromResponse = getPageFromResponse(response);
        }
        return accumulate;
    }

    private Response<List<GithubReaction>> getReactions(String owner, String repo, Integer number, String token, Integer page) throws IOException {
        return issueService.issueReactions(owner, repo, number, token, page).execute();
    }

    private Integer getPageFromResponse(Response response) throws UnsupportedEncodingException {
        String link = response.headers().get("Link");
        if (link != null) {
            String[] parts = link.split(",");
            Map<RelType, PaginationLink> linkMap = new HashMap<>();
            for (String part : parts) {
                PaginationLink paginationLink = new PaginationLink(part);
                linkMap.put(paginationLink.rel, paginationLink);
            }

            PaginationLink next = linkMap.get(RelType.next);
            if (next != null) {
                return next.page;
            }
        }
        return null;
    }
}
