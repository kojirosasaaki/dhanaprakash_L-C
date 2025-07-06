package com.newsapp.news_aggregator.controller;

import com.newsapp.news_aggregator.model.News;
import com.newsapp.news_aggregator.repository.NewsRepository;
import com.newsapp.news_aggregator.service.ExternalNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private ExternalNewsService externalNewsService;

    @Autowired
    private NewsRepository newsRepo;

    @GetMapping
    public List<News> getAllNews() {
        List<News> original = newsRepo.findAll();
        List<News> onlyDescriptions = new ArrayList<News>();
        for (int i = 0; i < original.size(); i++) {
            News news = new News();
            news.setDescription(original.get(i).getDescription());
            onlyDescriptions.add(news);
        }
        return onlyDescriptions;
    }

    @GetMapping("/fetch")
    public String fetchNews() {
        return externalNewsService.fetchNews(true);
    }
}