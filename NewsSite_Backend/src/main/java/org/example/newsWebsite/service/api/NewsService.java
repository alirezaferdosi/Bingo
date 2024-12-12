package org.example.newsWebsite.service.api;

import org.example.newsWebsite.model.News;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("News")
public interface NewsService {
    News addNews(News news);

    News editNews(Long id, News news);

    List<News> getAllNews();

    List<News> getAllNewsconfirmed();

    List<News> getNewsByCategory(String category);

    Integer incrementNewsViewer(Long id);

    boolean deleteNews(Long id);

    News getNews(Long id);
}
