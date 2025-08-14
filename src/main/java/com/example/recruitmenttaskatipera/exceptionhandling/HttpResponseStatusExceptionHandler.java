package com.example.recruitmenttaskatipera.exceptionhandling;

import com.example.recruitmenttaskatipera.exceptionhandling.dto.StatusResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class HttpResponseStatusExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StatusResponseDTO> handleResponseStatusException(ResponseStatusException ex) {
        StatusResponseDTO errorResponse = new StatusResponseDTO(
                ex.getStatusCode().value(),
                ex.getReason()
        );

        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

}
