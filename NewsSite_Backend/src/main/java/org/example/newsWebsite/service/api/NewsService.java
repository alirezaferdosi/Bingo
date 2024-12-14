package org.example.newsWebsite.service.api;

import org.example.newsWebsite.model.News;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("News")
public interface NewsService {
    News addNews(News news);

    News editNews(News news);

    List<News> getAllNews();

    List<News> getAllNewsConfirmed();

    List<News> getNewsByCategory(String category);

    List<News> getNewsByName(String name);

    Integer incrementNewsViewer(Long id);

    boolean deleteNews(Long id);

    News getNewsById(Long id);

    News getNews(Long id);

    boolean isExistNews(Long id);
}
