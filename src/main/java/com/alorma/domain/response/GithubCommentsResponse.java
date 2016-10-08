package com.alorma.domain.response;

import com.alorma.domain.GithubComment;

import java.util.ArrayList;
import java.util.List;

public class GithubCommentsResponse {
    private int total_count;
    private List<GithubComment> comments;

    public GithubCommentsResponse() {
        this.total_count = 0;
        this.comments = new ArrayList<>();
    }

    public GithubCommentsResponse(List<GithubComment> comments) {
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
