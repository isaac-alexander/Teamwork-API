package com.alexander.teamwork_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class GifResponse {

    // GIF ID.
    private Long id;

    // Title of the GIF.
    private String title;

    // Name of the uploaded GIF file.
    private String fileName;

    // URL used to view the uploaded GIF.
    private String fileUrl;

    // Employee who uploaded the GIF.
    private String author;

    // Date the GIF was uploaded.
    private LocalDateTime createdAt;

}