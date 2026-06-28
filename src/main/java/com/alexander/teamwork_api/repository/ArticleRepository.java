package com.alexander.teamwork_api.repository;

import com.alexander.teamwork_api.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

//    Returns all articles ordered from newest to oldest.
    List<Article> findAllByOrderByCreatedAtDesc();

}