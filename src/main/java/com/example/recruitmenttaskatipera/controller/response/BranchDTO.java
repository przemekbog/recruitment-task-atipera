package com.example.recruitmenttaskatipera.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BranchDTO {
    public String name;
    public String lastCommitSha;
}
