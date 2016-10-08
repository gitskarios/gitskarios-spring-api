package com.alorma.data;

import com.alorma.data.github.IssueService;
import com.alorma.domain.Issue;
import com.alorma.domain.IssueRepository;

import java.io.IOException;

public class GithubIssueRepository implements IssueRepository {

    private final IssueService issueService;

    public GithubIssueRepository(IssueService issueService) {
        this.issueService = issueService;
    }

    @Override
    public Issue getIssue(String owner, String repo, Integer number) throws IOException {
        return issueService.getIssue(owner, repo, number).execute().body();
    }
}
