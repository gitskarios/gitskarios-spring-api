package com.alorma.domain.response;

import com.alorma.domain.Issue;

public class IssueResponse {
    private Issue issue;
    private IssueStory story;

    public IssueResponse(Issue issue, IssueStory story) {
        this.issue = issue;
        this.story = story;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public IssueStory getStory() {
        return story;
    }

    public void setStory(IssueStory story) {
        this.story = story;
    }
}
