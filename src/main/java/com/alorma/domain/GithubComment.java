package com.alorma.domain;

import java.util.Date;

public class GithubComment implements GithubIssueStoryItem {

  private static final int MAX_MESSAGE_LENGHT = 146;
  public long id;
  public String body;
  public String body_html;
  public User user;
  public Date created_at;
  public Date updated_at;

  public GithubComment() {
  }

  public String shortMessage() {
    if (body != null) {
      if (body.length() > MAX_MESSAGE_LENGHT) {
        return body.substring(0, MAX_MESSAGE_LENGHT).concat("...");
      } else {
        return body;
      }
    }
    return null;
  }

  public static int getMaxMessageLenght() {
    return MAX_MESSAGE_LENGHT;
  }

  public long getId() {
    return id;
  }

  public String getBody() {
    return body;
  }

  public String getBody_html() {
    return body_html;
  }

  public User getUser() {
    return user;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  @Override
  public long getTime() {
    return created_at.getTime();
  }

  @Override
  public GithubStoryItemType getType() {
    return GithubStoryItemType.comment;
  }
}
