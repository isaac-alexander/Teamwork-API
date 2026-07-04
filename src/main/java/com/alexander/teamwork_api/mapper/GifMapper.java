package com.alexander.teamwork_api.mapper;

import com.alexander.teamwork_api.dto.GifResponse;
import com.alexander.teamwork_api.entity.Gif;

public class GifMapper {

    // Prevents creating objects of this class.
    private GifMapper() {
    }

    // Converts a Gif entity into a GifResponse DTO.
    public static GifResponse toGifResponse(Gif gif) {

        return GifResponse.builder()
                .id(gif.getId())
                .title(gif.getTitle())
                .fileName(gif.getFileName())
                .fileUrl("/api/gifs/files/" + gif.getFileName())
                .author(gif.getAuthor().getEmail())
                .createdAt(gif.getCreatedAt())
                .build();
    }

}