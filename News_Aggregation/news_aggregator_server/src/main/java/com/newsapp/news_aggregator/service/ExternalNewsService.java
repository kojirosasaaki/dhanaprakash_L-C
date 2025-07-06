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

import java.util.List;

@Service
public class ExternalNewsService {

    private NewsRepository newsRepo;
    private RestTemplate rest = new RestTemplate();
    private ObjectMapper mapper = new ObjectMapper();

    @Value("${newsapi.base-url}")
    private String baseUrl;

    @Value("${newsapi.token}")
    private String apiToken;

    @Value("${newsapi.language}")
    private String language;

    @Value("${newsapi.locale}")
    private String locale;

    public ExternalNewsService(NewsRepository newsRepo) {
        this.newsRepo = newsRepo;
    }

    @Scheduled(fixedRate = 60000)
    public void autoFetchNews() {
        if (UserLoginTracker.isLoggedIn()) {
            fetchNewsInternal(false, false);
        }
    }

    public String fetchNews(boolean printDescriptions) {
        StringBuilder output = new StringBuilder();
        output.append("\nFollowing is the news fetched:-\n\n");
        output.append(fetchNewsInternal(printDescriptions, true));
        return output.toString();
    }

    private String fetchNewsInternal(boolean printDescriptions, boolean returnOutput) {
        StringBuilder result = new StringBuilder();
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("api_token", apiToken)
                .queryParam("language", language)
                .queryParam("locale", locale)
                .toUriString();

        try {
            String json = rest.getForObject(url, String.class);
            JsonNode root = mapper.readTree(json);
            JsonNode dataArray = root.path("data");

            List<News> existingNews = newsRepo.findAll();

            for (int i = 0; i < dataArray.size(); i++) {
                JsonNode item = dataArray.get(i);
                String title = item.path("title").asText();
                String description = item.path("description").asText();

                if (title == null || title.trim().equals("")) continue;
                if (description == null || description.trim().equals("")) continue;

                boolean alreadyExists = false;
                for (int j = 0; j < existingNews.size(); j++) {
                    News oldNews = existingNews.get(j);
                    if (oldNews.getTitle() != null && oldNews.getTitle().equals(title)) {
                        alreadyExists = true;
                        break;
                    }
                }

                if (alreadyExists) {
                    if (printDescriptions && returnOutput) {
                        result.append("Already exists: ").append(description).append("\n");
                    }
                    continue;
                }

                News news = new News();
                news.setTitle(title);
                news.setDescription(description);
                newsRepo.save(news);

                if (printDescriptions && returnOutput) {
                    result.append("News Description: ").append(description).append("\n");
                }
            }

        } catch (Exception e) {
            result.append("Could not connect to news API: ").append(e.getMessage());
        }

        return result.toString();
    }
}
