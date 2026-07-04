package com.alexander.teamwork_api.service;

import com.alexander.teamwork_api.dto.GifCommentRequest;
import com.alexander.teamwork_api.dto.GifCommentResponse;
import com.alexander.teamwork_api.entity.Gif;
import com.alexander.teamwork_api.entity.GifComment;
import com.alexander.teamwork_api.entity.User;
import com.alexander.teamwork_api.mapper.GifCommentMapper;
import com.alexander.teamwork_api.repository.GifCommentRepository;
import com.alexander.teamwork_api.repository.GifRepository;
import com.alexander.teamwork_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GifCommentService {

    private final GifCommentRepository gifCommentRepository;
    private final GifRepository gifRepository;
    private final UserRepository userRepository;

    // Adds a comment to a GIF.
    public GifCommentResponse addComment(
            Long gifId,
            GifCommentRequest request,
            Authentication authentication) {

        // Get logged-in user email from JWT
        String email = authentication.getName();

        // Find the user
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Find the GIF being commented on
        Gif gif = gifRepository.findById(gifId)
                .orElseThrow(() -> new RuntimeException("GIF not found"));

        // Create comment entity
        GifComment comment = GifComment.builder()
                .content(request.getContent())
                .gif(gif)
                .author(author)
                .build();

        // Save comment
        GifComment savedComment = gifCommentRepository.save(comment);

        // Return response DTO
        return GifCommentMapper.toGifCommentResponse(savedComment);
    }

    // Gets all comments for a GIF
    public List<GifCommentResponse> getComments(Long gifId) {

        // Ensure GIF exists
        gifRepository.findById(gifId)
                .orElseThrow(() -> new RuntimeException("GIF not found"));

        // Fetch comments sorted by oldest first
        List<GifComment> comments =
                gifCommentRepository.findByGifIdOrderByCreatedAtAsc(gifId);

        // Map to response DTOs
        return comments.stream()
                .map(GifCommentMapper::toGifCommentResponse)
                .toList();
    }
}