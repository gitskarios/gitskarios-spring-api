package com.alorma.domain;

import com.alorma.data.exception.UnauthorizedException;
import com.alorma.domain.response.IssueResponse;

import java.io.IOException;
import java.util.List;

public interface IssueRepository {
    IssueResponse getIssue(String owner, String repo, Integer number, String token) throws Exception, UnauthorizedException;
}
