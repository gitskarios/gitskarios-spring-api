package com.alorma.domain.response;

import com.alorma.domain.GithubComment;

import java.util.ArrayList;
import java.util.List;

public class IssueStory {
    private int total_count;
    private List<GithubComment> comments;

    public IssueStory() {
        this.total_count = 0;
        this.comments = new ArrayList<>();
    }

    public IssueStory(List<GithubComment> comments) {
        this.total_count = comments.size();
        this.comments = comments;
    }

    public int getTotal_count() {
        return total_count;
    }

    public List<GithubComment> getComments() {
        return comments;
    }
}
