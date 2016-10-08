package com.alorma.view;

import com.alorma.domain.IssueRepository;
import com.alorma.domain.response.IssueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class IssueController {

    @Autowired @Qualifier("github")
    private IssueRepository issueRepository;

    @RequestMapping("/repos/{owner}/{repo}/issues/{number}")
    public IssueResponse getIssue(@PathVariable(value = "owner") String owner, @PathVariable(value = "repo") String repo, @PathVariable(value = "number") Integer number) throws IOException {
        return issueRepository.getIssue(owner, repo, number);
    }
}
