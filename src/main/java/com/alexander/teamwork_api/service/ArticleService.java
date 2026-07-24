package com.alexander.teamwork_api.service;

import com.alexander.teamwork_api.dto.ArticleRequest;
import com.alexander.teamwork_api.dto.ArticleResponse;
import com.alexander.teamwork_api.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface ArticleService {

    // Creates a new article.
    ArticleResponse createArticle(ArticleRequest request, Authentication authentication);

    // Returns an article entity using its ID.
    Article getArticleByIdEntity(Long id);

    // Returns paginated articles.
    Page<ArticleResponse> getAllArticles(int page, int size);

    // Returns one article.
    ArticleResponse getArticleById(Long id);

    // Updates an article.
    ArticleResponse updateArticle(Long id, ArticleRequest request, Authentication authentication);

    // Deletes an article.
    void deleteArticle(Long id, Authentication authentication);

}