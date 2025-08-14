package com.example.recruitmenttaskatipera;

import com.example.recruitmenttaskatipera.controller.response.BranchDTO;
import com.example.recruitmenttaskatipera.controller.response.RepositoryDTO;
import com.example.recruitmenttaskatipera.githubapi.GithubAPIClient;
import com.example.recruitmenttaskatipera.githubapi.GithubAPIConfig;
import com.example.recruitmenttaskatipera.githubapi.dto.GithubBranchPartialDTO;
import com.example.recruitmenttaskatipera.githubapi.dto.GithubCommitPartialDTO;
import com.example.recruitmenttaskatipera.githubapi.dto.GithubOwnerPartialDTO;
import com.example.recruitmenttaskatipera.githubapi.dto.GithubRepositoryPartialDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(RecruitmentTaskAtiperaApplicationTests.TestConfig.class)
class RecruitmentTaskAtiperaApplicationTests {
	private static final String TEST_USER_NAME = "someuser";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void mainHappyPathIntegrationTest() {
		String url = String.format("http://localhost:%d/%s/repos", port, TEST_USER_NAME);
		ResponseEntity<RepositoryDTO[]> response = restTemplate.getForEntity(url, RepositoryDTO[].class);

		assertThat(response.getBody()).isEqualTo(new RepositoryDTO[] {
				new RepositoryDTO("repo1", TEST_USER_NAME, List.of(
						new BranchDTO("main", "somesha1"),
						new BranchDTO("br2", "somesha2")
				)),
				new RepositoryDTO("repo2", TEST_USER_NAME, List.of(
						new BranchDTO("main", "somesha3")
				)),
				new RepositoryDTO("repo3", TEST_USER_NAME, List.of())
		});
	}

	@TestConfiguration
	static class TestConfig {
		@Bean
		@Primary
		GithubAPIClient gitHubClient() {
			GithubAPIClient mock = Mockito.mock(GithubAPIClient.class);
			Mockito.when(mock.getUserRepositories(TEST_USER_NAME)).thenReturn(Optional.of(List.of(
					new GithubRepositoryPartialDTO("repo1", new GithubOwnerPartialDTO(TEST_USER_NAME)),
					new GithubRepositoryPartialDTO("repo2", new GithubOwnerPartialDTO(TEST_USER_NAME)),
					new GithubRepositoryPartialDTO("repo3", new GithubOwnerPartialDTO(TEST_USER_NAME))
			)));
			Mockito.when(mock.getRepositoryBranches(TEST_USER_NAME, "repo1")).thenReturn(Optional.of(List.of(
					new GithubBranchPartialDTO("main", new GithubCommitPartialDTO("somesha1")),
					new GithubBranchPartialDTO("br2", new GithubCommitPartialDTO("somesha2"))
			)));
			Mockito.when(mock.getRepositoryBranches(TEST_USER_NAME, "repo2")).thenReturn(Optional.of(List.of(
					new GithubBranchPartialDTO("main", new GithubCommitPartialDTO("somesha3"))
			)));
			Mockito.when(mock.getRepositoryBranches(TEST_USER_NAME, "repo3")).thenReturn(Optional.of(List.of()));

			return mock;
		}

		@Bean
		@Primary
		GithubAPIConfig githubAPIConfig() {
			return Mockito.mock(GithubAPIConfig.class);
		}
	}
}
