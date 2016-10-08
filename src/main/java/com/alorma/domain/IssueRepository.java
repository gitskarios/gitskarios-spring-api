package com.alorma.domain;

import java.io.IOException;

public interface IssueRepository {
    Issue getIssue(String owner, String repo, Integer number) throws IOException;
}
