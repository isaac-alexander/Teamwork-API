package com.alexander.teamwork_api.mapper;

import com.alexander.teamwork_api.dto.GifCommentResponse;
import com.alexander.teamwork_api.entity.GifComment;

public class GifCommentMapper {

    // Prevents creating objects of this class.
    private GifCommentMapper() {
    }

    // Converts a GifComment entity into a GifCommentResponse DTO.
    public static GifCommentResponse toGifCommentResponse(GifComment comment) {

        return GifCommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getAuthor().getEmail())
                .createdAt(comment.getCreatedAt())
                .build();
    }

}