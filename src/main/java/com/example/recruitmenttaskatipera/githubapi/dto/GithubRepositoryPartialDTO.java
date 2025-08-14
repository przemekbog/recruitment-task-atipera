package com.example.recruitmenttaskatipera.githubapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepositoryPartialDTO {
    private String name;
    private GithubOwnerPartialDTO owner;
}
