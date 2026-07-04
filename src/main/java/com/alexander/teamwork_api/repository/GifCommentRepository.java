package com.alexander.teamwork_api.repository;

import com.alexander.teamwork_api.entity.GifComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GifCommentRepository extends JpaRepository<GifComment, Long> {

    // Returns all comments for a GIF ordered from oldest to newest.
    List<GifComment> findByGifIdOrderByCreatedAtAsc(Long gifId);

}