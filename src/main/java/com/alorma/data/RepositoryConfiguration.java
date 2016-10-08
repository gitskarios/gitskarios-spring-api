package com.alorma.data;

import com.alorma.data.github.IssueService;
import com.alorma.domain.GithubComment;
import com.alorma.domain.Issue;
import com.alorma.domain.IssueRepository;
import com.alorma.domain.response.GithubCommentsResponse;
import com.alorma.domain.response.IssueResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RepositoryConfiguration {

    @Bean(name = {"github"})
    public IssueRepository provideGithubRepository(IssueService issueService) {
        return new GithubIssueRepository(issueService);
    }

    @Bean(name = {"mock"})
    public IssueRepository provideMockRepository() {
        return new IssueRepository() {
            @Override
            public IssueResponse getIssue(String owner, String repo, Integer number) throws IOException {
                Issue issue = new Issue();
                issue.setTitle("Mock");
                issue.setTitle("Title mock");
                return new IssueResponse(issue, new GithubCommentsResponse());
            }

            @Override
            public List<GithubComment> getIssueComments(String owner, String repo, Integer number) throws IOException {
                return new ArrayList<>();
            }
        };
    }
}
