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
        List<News> allNews = newsRepo.findAll();
        List<News> onlyDescriptionList = new ArrayList<News>();
        for (int i = 0; i < allNews.size(); i++) {
            News original = allNews.get(i);
            if (original.getDescription() != null && !original.getDescription().trim().isEmpty()) {
                News n = new News();
                n.setDescription(original.getDescription());
                onlyDescriptionList.add(n);
            }
        }
        return onlyDescriptionList;
    }

    @GetMapping("/fetch")
    public String fetchNews() {
        return externalNewsService.fetchNews(true);
    }
}
