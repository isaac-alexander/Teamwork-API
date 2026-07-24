package com.alexander.teamwork_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse<T>{

    // Indicates whether the request was successful.
    private boolean success;

    // Describes what went wrong.
    private T message;

    // HTTP status code.
    private int status;

    // Time the error occurred.
    private LocalDateTime timestamp;

}