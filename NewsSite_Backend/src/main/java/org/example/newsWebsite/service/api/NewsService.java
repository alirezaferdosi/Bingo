package org.example.newsWebsite.service.api;

import org.example.newsWebsite.model.News;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("News")
public interface NewsService {
    @Transactional
    News addNews(News news);

    @Transactional
    News editNews(News news);

    @Transactional
    List<News> getAllNews();

    @Transactional
    List<News> getAllNewsConfirmed();

    @Transactional
    List<News> getNewsByCategory(String category);

    @Transactional
    List<News> getNewsByName(String name);

    @Transactional
    Integer incrementNewsViewer(Long newsId, Long userId);

    @Transactional
    boolean deleteNews(Long id);

    @Transactional
    News getNewsById(Long id);

    @Transactional
    News getNews(Long id);

    @Transactional
    boolean isExistNews(Long id);

    @Transactional
    List<News> getLastNews(Long time);

    @Transactional
    List<News> getLastNews(Long time, String category);

    List<News> getMostViews(Long time, Integer limit);

    List<News> getMostViews(Long time, Integer limit, String category);
}
