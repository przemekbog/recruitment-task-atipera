package com.example.recruitmenttaskatipera.githubapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubBranchPartialDTO {
    private String name;
    private GithubCommitPartialDTO commit;
}
