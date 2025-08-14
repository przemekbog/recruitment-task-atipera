package com.example.recruitmenttaskatipera.githubapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepositoryPartialDTO {
    private String name;
    private GithubOwnerPartialDTO owner;
}
