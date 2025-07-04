package com.newsapp.news_aggregator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsapp.news_aggregator.model.News;
import com.newsapp.news_aggregator.repository.NewsRepository;
import com.newsapp.news_aggregator.util.UserLoginTracker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ExternalNewsService {

    private RestTemplate rest = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();
    private NewsRepository newsRepo;

    @Value("${newsapi.base-url}")
    private String baseUrl;

    @Value("${newsapi.token}")
    private String apiToken;

    @Value("${newsapi.language}")
    private String language;

    @Value("${newsapi.limit}")
    private String limit;

    public ExternalNewsService(NewsRepository newsRepo) {
        this.newsRepo = newsRepo;
    }

    @Scheduled(fixedRate = 20000)
    public void scheduledFetchNews() {
        fetchNews(false);
    }

    public String fetchNews(boolean printDescriptions) {
        String url = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("language", language)
                .queryParam("limit", limit)
                .queryParam("api_token", apiToken)
                .toUriString();

        try {
            String json = rest.getForObject(url, String.class);
            JsonNode root = mapper.readTree(json).path("data");

            int newCount = 0;

            for (int i = 0; i < root.size(); i++) {
                JsonNode item = root.get(i);
                String title = item.path("title").asText();
                String description = item.path("description").asText();

                if (title == null || title.trim().equals("")) {
                    continue;
                }

                if (description == null || description.trim().equals("")) {
                    continue;
                }

                if (newsRepo.existsByTitle(title)) {
                    continue;
                }

                News news = new News();
                news.setTitle(title);
                news.setDescription(description);
                newsRepo.save(news);

                if (UserLoginTracker.isLoggedIn() && printDescriptions) {
                    System.out.println("News Description: " + description);
                }

                newCount++;
            }

            if (newCount == 0) {
                return "No new news available.";
            }

            return "News fetched successfully.";

        } catch (Exception e) {
            return "Could not connect to news API: " + e.getMessage();
        }
    }
}
