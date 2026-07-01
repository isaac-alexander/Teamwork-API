package com.alexander.teamwork_api.service;

import com.alexander.teamwork_api.dto.CommentRequest;
import com.alexander.teamwork_api.dto.CommentResponse;
import com.alexander.teamwork_api.entity.Article;
import com.alexander.teamwork_api.entity.Comment;
import com.alexander.teamwork_api.entity.User;
import com.alexander.teamwork_api.mapper.CommentMapper;
import com.alexander.teamwork_api.repository.ArticleRepository;
import com.alexander.teamwork_api.repository.CommentRepository;
import com.alexander.teamwork_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    // Adds a comment to an article.
    public CommentResponse addComment(
            Long articleId,
            CommentRequest request,
            Authentication authentication) {

        // Gets the email of the logged-in user.
        String email = authentication.getName();

        // Finds the logged-in user.
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Finds the article being commented on.
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        // Creates a new comment.
        Comment comment = Comment.builder()
                .content(request.getContent())
                .author(author)
                .article(article)
                .build();

        // Saves the comment.
        Comment savedComment = commentRepository.save(comment);

        // Converts the entity into a response DTO.
        return CommentMapper.toCommentResponse(savedComment);
    }

}