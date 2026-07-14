package com.alexander.teamwork_api.service;

import com.alexander.teamwork_api.dto.ArticleRequest;
import com.alexander.teamwork_api.dto.ArticleResponse;
import com.alexander.teamwork_api.dto.CommentResponse;
import com.alexander.teamwork_api.entity.Article;
import com.alexander.teamwork_api.entity.User;
import com.alexander.teamwork_api.exception.ArticleNotFoundException;
import com.alexander.teamwork_api.exception.UnauthorizedActionException;
import com.alexander.teamwork_api.exception.UserNotFoundException;
import com.alexander.teamwork_api.mapper.ArticleMapper;
import com.alexander.teamwork_api.mapper.CommentMapper;
import com.alexander.teamwork_api.repository.ArticleRepository;
import com.alexander.teamwork_api.repository.CommentRepository;
import com.alexander.teamwork_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // Creates a new article for the logged-in employee.
    public ArticleResponse createArticle(
            ArticleRequest request,
            Authentication authentication) {

        // Gets the email of the logged-in user.
        String email = authentication.getName();

        // Finds the logged-in user.
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Creates a new article.
        Article article = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(author)
                .build();

        // Saves the article.
        Article savedArticle = articleRepository.save(article);

        return ArticleMapper.toArticleResponse(
                savedArticle,
                List.of());
    }

    // Returns articles ordered from newest to oldest.
    public Page<ArticleResponse> getAllArticles(
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return articleRepository
                .findAllByOrderByCreatedAtDesc(pageable)
                .map(ArticleMapper::toArticleResponse);
    }

    // Returns one article by its ID.
    public ArticleResponse getArticleById(Long id) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        List<CommentResponse> comments = commentRepository
                .findByArticleIdOrderByCreatedAtAsc(article.getId())
                .stream()
                .map(CommentMapper::toCommentResponse)
                .toList();

        return ArticleMapper.toArticleResponse(
                article,
                comments
        );
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
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        // Checks if the logged-in user is the author.
        if (!article.getAuthor().getEmail().equals(email)) {
            throw new UnauthorizedActionException("You can only update your own article");
        }

        // Updates the article.
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());

        // Saves the updated article.
        Article updatedArticle = articleRepository.save(article);

        List<CommentResponse> comments = commentRepository
                .findByArticleIdOrderByCreatedAtAsc(updatedArticle.getId())
                .stream()
                .map(CommentMapper::toCommentResponse)
                .toList();

        return ArticleMapper.toArticleResponse(
                updatedArticle,
                comments
        );
    }

    // Deletes an article if it belongs to the logged-in employee.
    public void deleteArticle(
            Long id,
            Authentication authentication) {

        // Gets the email of the logged-in user.
        String email = authentication.getName();

        // Finds the article.
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found"));

        // Checks if the logged-in user is the author.
        if (!article.getAuthor().getEmail().equals(email)) {
            throw new UnauthorizedActionException("You can only delete your own article");
        }

        // Deletes the article.
        articleRepository.delete(article);
    }

}