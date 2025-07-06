package com.newsapp.news_aggregator.repository;

import com.newsapp.news_aggregator.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    boolean existsByTitle(String title);
}
