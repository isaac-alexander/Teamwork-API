package com.alexander.teamwork_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ArticleResponse {
    private Long id;

    private String title;

    private String content;

    private String author;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}