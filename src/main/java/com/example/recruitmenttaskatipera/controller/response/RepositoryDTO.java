package com.example.recruitmenttaskatipera.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RepositoryDTO {
    private String name;
    private String ownerLogin;
    private List<BranchDTO> branches;
}
