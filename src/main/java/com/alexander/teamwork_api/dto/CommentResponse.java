package com.alexander.teamwork_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CommentResponse {

    // Comment ID.
    private Long id;

    // Comment written by the employee.
    private String content;

    // Email of the employee who wrote the comment.
    private String author;

    // Date and time the comment was created.
    private LocalDateTime createdAt;

}