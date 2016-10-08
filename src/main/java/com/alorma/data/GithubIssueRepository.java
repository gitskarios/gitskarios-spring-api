package com.alorma.data;

import com.alorma.data.github.IssueService;
import com.alorma.domain.*;
import com.alorma.domain.response.GithubCommentsResponse;
import com.alorma.domain.response.IssueResponse;
import retrofit2.Response;
import rx.Observable;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GithubIssueRepository implements IssueRepository {

    private final IssueService issueService;

    public GithubIssueRepository(IssueService issueService) {
        this.issueService = issueService;
    }

    @Override
    public IssueResponse getIssue(String owner, String repo, Integer number) throws IOException {
        Issue issue = issueService.getIssue(owner, repo, number).execute().body();
        List<GithubComment> comments = getIssueComments(owner, repo, number);
        return new IssueResponse(issue, new GithubCommentsResponse(comments));
    }

    @Override
    public List<GithubComment> getIssueComments(String owner, String repo, Integer number) throws IOException {
        return Observable.defer(() -> {
            try {
                return Observable.just(getAllComments(owner, repo, number));
            } catch (IOException e) {
                return Observable.error(e);
            }
        }).toBlocking().first();
    }

    private List<GithubComment> getAllComments(String owner, String repo, Integer number) throws IOException {
        Response<List<GithubComment>> response = getComments(owner, repo, number, null);
        Integer pageFromResponse = getPageFromResponse(response);

        List<GithubComment> accumulate = new ArrayList<>();
        accumulate.addAll(response.body());
        while (pageFromResponse != null) {
            response = getComments(owner, repo, number, pageFromResponse);
            accumulate.addAll(response.body());
            pageFromResponse = getPageFromResponse(response);
        }
        return accumulate;
    }

    private Response<List<GithubComment>> getComments(String owner, String repo, Integer number, Integer page) throws IOException {
        return issueService.comments(owner, repo, number, page).execute();
    }

    private Integer getPageFromResponse(Response<List<GithubComment>> response) throws UnsupportedEncodingException {
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
