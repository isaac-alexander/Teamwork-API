package com.alexander.teamwork_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    private String imageUrl;

    private String publicId;

    // Date the GIF was uploaded.
    private LocalDateTime createdAt;

    // Employee who uploaded the GIF.
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    // Stores all comments made on this GIF.
    @OneToMany(mappedBy = "gif", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GifComment> comments = new ArrayList<>();

    // Automatically sets the upload time.
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}