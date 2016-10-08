package com.alorma.domain;

public class GithubReaction {
    private long id;
    private User user;
    private String content;

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }
}
