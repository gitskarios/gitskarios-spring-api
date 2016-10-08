package com.alorma.domain.response;

import com.alorma.domain.GithubIssueStoryItem;

import java.util.ArrayList;
import java.util.List;

public class IssueStory {
    private int total_events_count;
    private List<GithubIssueStoryItem> events;

    public IssueStory() {
        this.total_events_count = 0;
        this.events = new ArrayList<>();
    }

    public IssueStory(List<GithubIssueStoryItem> events) {
        this.total_events_count = events.size();
        this.events = events;
    }

    public int getTotal_events_count() {
        return total_events_count;
    }

    public List<GithubIssueStoryItem> getEvents() {
        return events;
    }
}
