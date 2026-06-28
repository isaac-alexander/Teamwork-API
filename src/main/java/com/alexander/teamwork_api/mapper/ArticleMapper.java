package com.alexander.teamwork_api.mapper;

import com.alexander.teamwork_api.dto.ArticleResponse;
import com.alexander.teamwork_api.entity.Article;

public class ArticleMapper {

    // Prevents creating objects of this class.
    private ArticleMapper() {
    }

    // Converts Article entity into ArticleResponse DTO.
    public static ArticleResponse toArticleResponse(Article article) {

        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getAuthor().getEmail())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

}