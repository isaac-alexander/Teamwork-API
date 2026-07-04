package com.alexander.teamwork_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class GifCommentResponse {

    // Comment ID.
    private Long id;

    // Comment text.
    private String content;

    // Employee who wrote the comment.
    private String author;

    // Date the comment was created.
    private LocalDateTime createdAt;

}