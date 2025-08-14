package com.example.recruitmenttaskatipera.githubapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubBranchPartialDTO {
    private String name;
    private GithubCommitPartialDTO commit;
}
