package com.alorma.data;

import com.alorma.data.github.IssueService;
import com.alorma.domain.Issue;
import com.alorma.domain.IssueRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean(name = {"github"})
    public IssueRepository provideGithubRepository(IssueService issueService) {
        return new GithubIssueRepository(issueService);
    }

    @Bean(name = {"mock"})
    public IssueRepository provideMockRepository() {
        return (owner, repo, number) -> {
            Issue issue = new Issue();
            issue.setTitle("Mock");
            issue.setTitle("Title mock");
            return issue;
        };
    }
}
