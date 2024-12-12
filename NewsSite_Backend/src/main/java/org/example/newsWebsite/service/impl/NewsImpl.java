package org.example.newsWebsite.service.impl;

import org.example.newsWebsite.model.News;
import org.example.newsWebsite.service.api.NewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
 class NewsImpl implements NewsService {
    @Override
    public News addNews(News news) {
        return null;
    }

    @Override
    public News editNews(Long id, News news) {
        return null;
    }

    @Override
    public List<News> getAllNews() {
        return List.of();
    }

    @Override
    public List<News> getAllNewsconfirmed() {
        return List.of();
    }

    @Override
    public List<News> getNewsByCategory(String category) {
        return List.of();
    }

    @Override
    public Integer incrementNewsViewer(Long id) {
        return 0;
    }

    @Override
    public boolean deleteNews(Long id) {
        return false;
    }

    @Override
    public News getNews(Long id) {
        return null;
    }
}
