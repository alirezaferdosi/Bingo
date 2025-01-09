package org.example.newsWebsite.service.api;

import org.example.newsWebsite.model.View;
import org.example.newsWebsite.model.News;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Comment")
public interface ViewService {
    View addComment(View comment);

    View editComment(Long id, View comment);

    boolean deleteComment(Long id);

    View getComment(Long id);

    List<View> getNewsComments(News news);
}
