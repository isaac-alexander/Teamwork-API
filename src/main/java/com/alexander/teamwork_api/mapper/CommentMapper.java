package com.alexander.teamwork_api.mapper;

import com.alexander.teamwork_api.dto.CommentResponse;
import com.alexander.teamwork_api.entity.Comment;

public class CommentMapper {

    // Prevents creating objects of this class.
    private CommentMapper() {
    }

    // Converts a Comment entity into a CommentResponse DTO.
    public static CommentResponse toCommentResponse(Comment comment) {

        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getAuthor().getEmail())
                .createdAt(comment.getCreatedAt())
                .build();
    }

}