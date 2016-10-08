package com.alorma.domain.response;

import com.alorma.domain.User;

import java.util.List;

public class GithubReactionResponse {
    private String content;
    private List<User> users;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
