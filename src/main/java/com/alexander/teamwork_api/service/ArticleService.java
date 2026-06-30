package com.alexander.teamwork_api.service;

import com.alexander.teamwork_api.dto.ArticleRequest;
import com.alexander.teamwork_api.dto.ArticleResponse;
import com.alexander.teamwork_api.entity.Article;
import com.alexander.teamwork_api.entity.User;
import com.alexander.teamwork_api.mapper.ArticleMapper;
import com.alexander.teamwork_api.repository.ArticleRepository;
import com.alexander.teamwork_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    // Creates a new article for the logged-in employee.
    public ArticleResponse createArticle(
            ArticleRequest request,
            Authentication authentication) {

        // Gets the email of the logged-in user.
        String email = authentication.getName();

        // Finds the logged-in user.
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Creates a new article.
        Article article = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(author)
                .build();

        // Saves the article.
        Article savedArticle = articleRepository.save(article);

        return ArticleMapper.toArticleResponse(savedArticle);
    }

    // Returns all articles from newest to oldest.
    public List<ArticleResponse> getAllArticles() {

        List<Article> articles =
                articleRepository.findAllByOrderByCreatedAtDesc();

        return articles.stream()
                .map(ArticleMapper::toArticleResponse)
                .toList();
    }

    // Returns one article by its ID.
    public ArticleResponse getArticleById(Long id) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        return ArticleMapper.toArticleResponse(article);
    }

    // Updates an article if it belongs to the logged-in employee.
    public ArticleResponse updateArticle(
            Long id,
            ArticleRequest request,
            Authentication authentication) {

        // Gets the email of the logged-in user.
        String email = authentication.getName();

        // Finds the article.
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        // Checks if the logged-in user is the author.
        if (!article.getAuthor().getEmail().equals(email)) {
            throw new RuntimeException("You can only update your own article");
        }

        // Updates the article.
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());

        // Saves the updated article.
        Article updatedArticle = articleRepository.save(article);

        return ArticleMapper.toArticleResponse(updatedArticle);
    }

    // Deletes an article if it belongs to the logged-in employee.
    public void deleteArticle(
            Long id,
            Authentication authentication) {

        // Gets the email of the logged-in user.
        String email = authentication.getName();

        // Finds the article.
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        // Checks if the logged-in user is the author.
        if (!article.getAuthor().getEmail().equals(email)) {
            throw new RuntimeException("You can only delete your own article");
        }

        // Deletes the article.
        articleRepository.delete(article);
    }

}