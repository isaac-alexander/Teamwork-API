package com.alexander.teamwork_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    // Primary key.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Comment written by the employee.
    @Column(nullable = false)
    private String content;

    // Date and time the comment was created.
    private LocalDateTime createdAt;

    // Many comments can belong to one article.
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    // Many comments can be written by one employee.
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    // Automatically sets the creation time.
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}