package com.alexander.teamwork_api.controller;

import com.alexander.teamwork_api.dto.ArticleRequest;
import com.alexander.teamwork_api.dto.ArticleResponse;
import com.alexander.teamwork_api.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public List<ArticleResponse> getAllArticles() {

        return articleService.getAllArticles();
    }

}