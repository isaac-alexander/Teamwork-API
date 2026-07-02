package com.alexander.teamwork_api.repository;

import com.alexander.teamwork_api.entity.Gif;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GifRepository extends JpaRepository<Gif, Long> {

    // Returns all GIFs ordered from newest to oldest.
    List<Gif> findAllByOrderByCreatedAtDesc();

}