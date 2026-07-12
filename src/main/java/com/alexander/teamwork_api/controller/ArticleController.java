package com.alexander.teamwork_api.controller;

import com.alexander.teamwork_api.dto.ArticleRequest;
import com.alexander.teamwork_api.dto.ArticleResponse;
import com.alexander.teamwork_api.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    // Creates a new article.
    @PostMapping
    public ArticleResponse createArticle(
            @RequestBody ArticleRequest request,
            Authentication authentication) {

        return articleService.createArticle(request, authentication);
    }

    // Returns all articles from newest to oldest.
    // Returns articles using pagination.
    @GetMapping
    public Page<ArticleResponse> getAllArticles(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size) {

        return articleService.getAllArticles(page, size);
    }

    // Returns one article using its ID.
    @GetMapping("/{id}")
    public ArticleResponse getArticleById(@PathVariable Long id) {

        return articleService.getArticleById(id);
    }

    // Updates an article.
    @PutMapping("/{id}")
    public ArticleResponse updateArticle(
            @PathVariable Long id,
            @RequestBody ArticleRequest request,
            Authentication authentication) {

        return articleService.updateArticle(id, request, authentication);
    }

    // Deletes an article.
    @DeleteMapping("/{id}")
    public String deleteArticle(
            @PathVariable Long id,
            Authentication authentication) {

        articleService.deleteArticle(id, authentication);

        return "Article deleted successfully.";
    }

}