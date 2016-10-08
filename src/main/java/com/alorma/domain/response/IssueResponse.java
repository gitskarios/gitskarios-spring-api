package com.alorma.domain.response;

import com.alorma.domain.Issue;

public class IssueResponse {
    private Issue issue;
    private GithubCommentsResponse comments;

    public IssueResponse(Issue issue, GithubCommentsResponse comments) {
        this.issue = issue;
        this.comments = comments;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public GithubCommentsResponse getComments() {
        return comments;
    }

    public void setComments(GithubCommentsResponse comments) {
        this.comments = comments;
    }
}
