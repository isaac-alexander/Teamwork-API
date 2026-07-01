package com.alexander.teamwork_api.controller;

import com.alexander.teamwork_api.dto.CommentRequest;
import com.alexander.teamwork_api.dto.CommentResponse;
import com.alexander.teamwork_api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Adds a comment to an article.
    @PostMapping("/{articleId}/comments")
    public CommentResponse addComment(
            @PathVariable Long articleId,
            @RequestBody CommentRequest request,
            Authentication authentication) {

        return commentService.addComment(
                articleId,
                request,
                authentication
        );
    }

}