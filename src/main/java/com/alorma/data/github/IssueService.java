package com.alorma.data.github;

import com.alorma.domain.Issue;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IssueService {
    @GET("/repos/{owner}/{repo}/issues/{number}")
    Call<Issue> getIssue(@Path("owner") String owner, @Path("repo") String repo, @Path("number") Integer number);
}
