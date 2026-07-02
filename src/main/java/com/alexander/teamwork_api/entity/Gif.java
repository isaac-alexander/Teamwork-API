package com.alexander.teamwork_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gifs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title of the GIF.
    @Column(nullable = false)
    private String title;

    // Name of the uploaded GIF file.
    @Column(nullable = false)
    private String fileName;

    // Date the GIF was uploaded.
    private LocalDateTime createdAt;

    // Employee who uploaded the GIF.
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    // Automatically sets the upload time.
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}