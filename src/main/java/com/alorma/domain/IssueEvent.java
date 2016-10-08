package com.alorma.domain;

import java.util.Date;

public class IssueEvent implements GithubIssueStoryItem {
    public long id;
    public String url;
    public User actor;
    public String event;
    public String commit_id;
    public Date created_at;
    public Label label;
    public Milestone milestone;
    public User assignee;
    public User assigner;
    public Rename rename;

    public IssueEvent() {
    }

    public long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public User getActor() {
        return actor;
    }

    public String getEvent() {
        return event;
    }

    public String getCommit_id() {
        return commit_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Label getLabel() {
        return label;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public User getAssignee() {
        return assignee;
    }

    public User getAssigner() {
        return assigner;
    }

    public Rename getRename() {
        return rename;
    }

    @Override
    public long getTime() {
        return created_at.getTime();
    }

    @Override
    public GithubStoryItemType getType() {
        return GithubStoryItemType.event;
    }
}