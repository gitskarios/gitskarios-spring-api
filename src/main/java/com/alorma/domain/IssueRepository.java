package com.alorma.domain;

import com.alorma.domain.response.IssueResponse;

import java.io.IOException;
import java.util.List;

public interface IssueRepository {
    IssueResponse getIssue(String owner, String repo, Integer number) throws IOException;
    List<GithubComment> getIssueComments(String owner, String repo, Integer number) throws IOException;
    List<IssueEvent> getIssueEvents(String owner, String repo, Integer number) throws IOException;
}
