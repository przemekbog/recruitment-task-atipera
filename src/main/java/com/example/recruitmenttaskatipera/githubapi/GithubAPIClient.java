package com.example.recruitmenttaskatipera.githubapi;

import com.example.recruitmenttaskatipera.githubapi.dto.GithubBranchPartialDTO;
import com.example.recruitmenttaskatipera.githubapi.dto.GithubRepositoryPartialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GithubAPIClient {
    private final GithubAPIConfig config;

    public GithubAPIClient(@Autowired GithubAPIConfig config) {
        this.config = config;
    }

    public Optional<List<GithubRepositoryPartialDTO>> getUserRepositories(String username)  {
        Optional<GithubRepositoryPartialDTO[]> repositories = makeGetGithubRequest(
                "https://api.github.com/users/{username}/repos",
                Map.of("username", username),
                GithubRepositoryPartialDTO[].class
        );

        return repositories.map(List::of);
    }

    public Optional<List<GithubBranchPartialDTO>> getRepositoryBranches(String username, String repository)  {
        Optional<GithubBranchPartialDTO[]> repositories = makeGetGithubRequest(
                "https://api.github.com/repos/{username}/{repository}/branches",
                Map.of(
                        "username", username,
                        "repository", repository
                ),
                GithubBranchPartialDTO[].class
        );

        return repositories.map(List::of);
    }

    private <T> Optional<T> makeGetGithubRequest(String urlPattern, Map<String, String> urlParameters, Class<T> responseType) {
        HttpEntity<String> entity = new HttpEntity<>(getRequestHeaders());

        RestTemplate template = new RestTemplate();
        try {
            ResponseEntity<T> response = template.exchange(
                    urlPattern,
                    HttpMethod.GET,
                    entity,
                    responseType,
                    urlParameters
            );

            T repositories = response.getBody();
            if(repositories == null) {
                return Optional.empty();
            }

            return Optional.of(repositories);
        } catch (HttpClientErrorException e) {
            return Optional.empty();
        }
    }

    private HttpHeaders getRequestHeaders() {
        String token = config.getToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization", "Bearer " + token);
        headers.set("X-GitHub-Api-Version", "2022-11-28");

        return headers;
    }


}
