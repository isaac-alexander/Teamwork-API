package com.alexander.teamwork_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "gif_comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GifComment {

    // Primary key.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Comment written by an employee.
    @Column(nullable = false)
    private String content;

    // Date and time the comment was created.
    private LocalDateTime createdAt;

    // Many comments can belong to one GIF.
    @ManyToOne
    @JoinColumn(name = "gif_id")
    private Gif gif;

    // Many comments can be written by one employee.
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    // Automatically sets the creation date before saving.
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}