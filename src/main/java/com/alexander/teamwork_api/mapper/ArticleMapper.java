package com.alexander.teamwork_api.mapper;

import com.alexander.teamwork_api.dto.CommentResponse;
import com.alexander.teamwork_api.dto.ArticleResponse;
import com.alexander.teamwork_api.entity.Article;

import java.util.List;

public class ArticleMapper {

    // Prevents creating objects of this class.
    private ArticleMapper() {
    }

    // Converts Article entity into ArticleResponse DTO.
    public static ArticleResponse toArticleResponse(
            Article article,
            List<CommentResponse> comments) {

        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getAuthor().getEmail())
                .comments(comments)
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

}