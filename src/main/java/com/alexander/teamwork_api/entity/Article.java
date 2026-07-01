package com.alexander.teamwork_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


// This class represents an article in the database.
// Every article belongs to exactly one employee.
// One employee can have many articles.

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {

//    Primary key.
//    Automatically increments whenever a new article is created.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    Title of the article.
    @Column(nullable = false)
    private String title;

//    Main body of the article.
//    @Lob allows long text to be stored.
    @Lob
    @Column(nullable = false)
    private String content;

//    Stores the date and time the article was created.
    private LocalDateTime createdAt;

//    Stores the date and time the article was last updated.
    private LocalDateTime updatedAt;


//    Many articles can belong to one employee.
//    The "author_id" column will be created in the articles table as a foreign key.
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

//    Automatically runs before the article is saved for the first time.
//    sets both createdAt and updatedAt.
    @PrePersist
    public void prePersist() {

        LocalDateTime now = LocalDateTime.now();

        createdAt = now;
        updatedAt = now;
    }

//    Automatically runs whenever the article is updated
//    Only updatedAt changes.

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}