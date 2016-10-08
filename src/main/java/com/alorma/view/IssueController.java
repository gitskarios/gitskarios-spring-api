package com.alorma.view;

import com.alorma.domain.IssueRepository;
import com.alorma.domain.response.IssueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class IssueController {

    @Autowired
    @Qualifier("github")
    private IssueRepository issueRepository;

    @RequestMapping("/repos/{owner}/{repo}/issues/{number}")
    public ResponseEntity<IssueResponse> getIssue(@PathVariable(value = "owner") String owner, @PathVariable(value = "repo") String repo, @PathVariable(value = "number") Integer number, @RequestParam(value = "token") String token) throws Exception {
        return new ResponseEntity<>(issueRepository.getIssue(owner, repo, number, token), HttpStatus.OK);
    }
}
