package com.example.recruitmenttaskatipera.exceptionhandling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusResponseDTO {
    @JsonProperty("status")
    private int statusCode;
    private String message;
}
