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

    @Transactional
    List<News> getMostViews(Long time, Integer limit);

    @Transactional
    List<News> getMostViews(Long time, Integer limit, String category);

    @Transactional
    Integer getAllVisits();

    @Transactional
    Integer getAllVisits(Long userId);

    @Transactional
    Integer getAllVisits(String username);

    @Transactional
    Integer getAllVisitsByCategory(String category);

    @Transactional
    Integer getNumberOfAllNews();

    @Transactional
    Integer getNumberOfAllNewsbyCategory(String category);

    @Transactional
    Integer getNumberOfAllNews(Long userId);

    @Transactional
    Integer getNumberOfAllNews(String username);

    @Transactional
    Boolean confirmNews(Long id);

    @Transactional
    Boolean rejectNews(Long id);
}