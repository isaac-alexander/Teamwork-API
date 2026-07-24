package com.alexander.teamwork_api.service;

import com.alexander.teamwork_api.dto.CommentRequest;
import com.alexander.teamwork_api.dto.CommentResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CommentService {

    // Adds a comment to an article.
    CommentResponse addComment(Long articleId, CommentRequest request, Authentication authentication);

    // Returns all comments for an article.
    List<CommentResponse> getCommentsByArticle(Long articleId);

}