package com.alorma.data.github;

import com.alorma.domain.GithubComment;
import com.alorma.domain.GithubReaction;
import com.alorma.domain.Issue;
import com.alorma.domain.IssueEvent;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IssueService {
    @GET("/repos/{owner}/{repo}/issues/{number}")
    Call<Issue> getIssue(@Path("owner") String owner, @Path("repo") String repo, @Path("number") Integer number
            , @Header("Authorization") String token);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    Call<List<GithubComment>> comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num
            , @Header("Authorization") String token
            , @Query("page") Integer page);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    Call<List<IssueEvent>> events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num
            , @Header("Authorization") String token
            , @Query("page") Integer page);

    @Headers({"Accept: application/vnd.github.squirrel-girl-preview"})
    @GET("/repos/{owner}/{name}/issues/{num}/reactions")
    Call<List<GithubReaction>> issueReactions(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num
            , @Header("Authorization") String token
            , @Query("page") Integer page);
}
