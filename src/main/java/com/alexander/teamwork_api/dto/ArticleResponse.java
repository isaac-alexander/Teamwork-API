package com.alexander.teamwork_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ArticleResponse {

    // Article ID.
    private Long id;

    // Title of the article.
    private String title;

    // Main article content.
    private String content;

    // Email of the employee who wrote the article.
    private String author;

    // Comments on this article.
    private List<CommentResponse> comments;

    // Date the article was created.
    private LocalDateTime createdAt;

    // Date the article was last updated.
    private LocalDateTime updatedAt;

}