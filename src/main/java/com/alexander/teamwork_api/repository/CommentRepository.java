package com.alexander.teamwork_api.repository;

import com.alexander.teamwork_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Returns all comments for an article ordered from oldest to newest.
    List<Comment> findByArticleIdOrderByCreatedAtAsc(Long articleId);

}