package com.example.recruitmenttaskatipera.controller;

import com.example.recruitmenttaskatipera.controller.response.BranchDTO;
import com.example.recruitmenttaskatipera.controller.response.RepositoryDTO;
import com.example.recruitmenttaskatipera.githubapi.GithubAPIClient;
import com.example.recruitmenttaskatipera.githubapi.dto.GithubBranchPartialDTO;
import com.example.recruitmenttaskatipera.githubapi.dto.GithubRepositoryPartialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@RestController
public class MainController {

    private final GithubAPIClient githubApiClient;

    public MainController(@Autowired GithubAPIClient githubApiClient) {
        this.githubApiClient = githubApiClient;
    }

    @GetMapping("{user}/repos")
    public List<RepositoryDTO> getRepositories(@PathVariable String user) {

        List<GithubRepositoryPartialDTO> userRepositories = githubApiClient
                .getUserRepositories(user)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Repositories for provided user could not be fetched"
                ));

        List<RepositoryDTO> responseRepositories = new ArrayList<>();
        for(GithubRepositoryPartialDTO repository : userRepositories) {
            List<GithubBranchPartialDTO> githubBranches = githubApiClient
                    .getRepositoryBranches(user, repository.getName())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Branches not found for repository " + repository.getName()
                    ));

            List<BranchDTO> responseBranches = githubBranches
                    .stream()
                    .map(branch -> new BranchDTO(
                            branch.getName(),
                            branch.getCommit().getSha()
                    ))
                    .toList();

            responseRepositories.add(new RepositoryDTO(
                    repository.getName(),
                    repository.getOwner().getLogin(),
                    responseBranches
            ));
        }

        return responseRepositories;
    }
}
