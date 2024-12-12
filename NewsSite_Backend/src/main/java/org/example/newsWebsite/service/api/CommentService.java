package org.example.newsWebsite.service.api;

import org.example.newsWebsite.model.Comment;
import org.example.newsWebsite.model.News;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("Comment")
public interface CommentService {
    Comment addComment(Comment comment);

    Comment editComment(Long id, Comment comment);

    boolean deleteComment(Long id);

    Comment getComment(Long id);

    List<Comment> getNewsComments(News news);
}
