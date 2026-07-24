package com.alexander.teamwork_api.controller;

import com.alexander.teamwork_api.dto.GifCommentRequest;
import com.alexander.teamwork_api.dto.GifCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gifs")
@RequiredArgsConstructor
public class GifCommentController {

//    private final GifCommentService gifCommentService;
//
//    // Adds a comment to a GIF.
//    @PostMapping("/{id}/comments")
//    public GifCommentResponse addComment(
//            @PathVariable Long id,
//            @RequestBody GifCommentRequest request,
//            Authentication authentication) {
//
//        return gifCommentService.addComment(id, request, authentication);
//    }
//
//    // Gets all comments for a GIF.
//    @GetMapping("/{id}/comments")
//    public List<GifCommentResponse> getComments(@PathVariable Long id) {
//
//        return gifCommentService.getComments(id);
//    }
}