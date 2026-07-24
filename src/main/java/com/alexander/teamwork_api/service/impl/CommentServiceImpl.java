package com.alexander.teamwork_api.service.impl;

import com.alexander.teamwork_api.dto.CommentRequest;
import com.alexander.teamwork_api.dto.CommentResponse;
import com.alexander.teamwork_api.entity.Article;
import com.alexander.teamwork_api.entity.Comment;
import com.alexander.teamwork_api.entity.User;
import com.alexander.teamwork_api.exception.ArticleNotFoundException;
import com.alexander.teamwork_api.mapper.CommentMapper;
import com.alexander.teamwork_api.repository.ArticleRepository;
import com.alexander.teamwork_api.repository.CommentRepository;
import com.alexander.teamwork_api.service.ArticleService;
import com.alexander.teamwork_api.service.CommentService;
import com.alexander.teamwork_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ArticleService articleService;
    private final UserService userService;

    // Adds a comment to an article.
    @Override
    public CommentResponse addComment(
            Long articleId,
            CommentRequest request,
            Authentication authentication) {

        User author = userService.getAuthenticatedUser(authentication);

        // Finds the article being commented on.
        Article article =
                articleService.getArticleByIdEntity(articleId);

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

    @Override
    public List<CommentResponse> getCommentsByArticle(
            Long articleId) {

        return commentRepository
                .findByArticleIdOrderByCreatedAtAsc(articleId)
                .stream()
                .map(CommentMapper::toCommentResponse)
                .toList();
    }

}