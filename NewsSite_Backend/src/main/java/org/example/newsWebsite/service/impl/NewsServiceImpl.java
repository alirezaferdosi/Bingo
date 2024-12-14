package org.example.newsWebsite.service.impl;

import org.example.newsWebsite.model.News;
import org.example.newsWebsite.repository.NewsRepository;
import org.example.newsWebsite.service.api.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
 class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public News addNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News editNews(News news) {
        if (news.getId() == null) {
            return null;
        }
        if (this.newsRepository.existsById(news.getId())) {
            News n = newsRepository.getById(news.getId());

            if(Objects.nonNull(news.getTitle()) && !news.getTitle().isEmpty()) {
                n.setTitle(news.getTitle());
            }
            if(Objects.nonNull(news.getNewsPath()) && !news.getNewsPath().isEmpty()) {
                n.setNewsPath(news.getNewsPath());
            }
            if(Objects.nonNull(news.getCategory()) && !news.getCategory().isEmpty()) {
                n.setCategory(news.getCategory());
            }
            if(Objects.nonNull(news.getDate())) {
                n.setDate(news.getDate());
            }
            if(Objects.nonNull(news.getPhotoPath()) && !news.getPhotoPath().isEmpty()) {
                n.setPhotoPath(news.getPhotoPath());
            }

            return newsRepository.save(n);
        }
        return null;
    }

    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> getAllNewsConfirmed() {
        List<News> news = new ArrayList<>();

        for(News n : newsRepository.findAll()) {
            if(n.getVerification())
                news.add(n);
        }
        return news;
    }

    @Override
    public List<News> getNewsByCategory(String category) {
        List<News> news = new ArrayList<>();

        for(News n : newsRepository.findAll()) {
            if(n.getCategory().equals(category))
                news.add(n);
        }
        return news;
    }

    @Override
    public List<News> getNewsByName(String name) {
        List<News> news = new ArrayList<>();

        for(News n : newsRepository.findAll()) {
            if(n.getTitle().equals(name))
                news.add(n);
        }
        return news;
    }

    @Override
    public News getNewsById(Long id) {
        if(newsRepository.existsById(id)) {
            return newsRepository.getById(id);
        }
        return null;
    }

    @Override
    public News getNews(Long id) {
        if(newsRepository.existsById(id)) {
            return newsRepository.getById(id);
        }
        return null;    }

    @Override
    public Integer incrementNewsViewer(Long id) {
        if (!this.newsRepository.existsById(id)) {
            return null;
        }
        News news = newsRepository.getById(id);
        news.setViewNumber(news.getViewNumber() + 1);
        newsRepository.save(news);
        return news.getViewNumber();
    }

    @Override
    public boolean deleteNews(Long id) {
        if(this.newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean isExistNews(Long id) {
        return newsRepository.existsById(id);
    }
}
