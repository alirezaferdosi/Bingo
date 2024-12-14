package org.example.newsWebsite.service.impl;

import org.example.newsWebsite.model.Comment;
import org.example.newsWebsite.model.News;
import org.example.newsWebsite.service.api.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public Comment addComment(Comment comment) {
        return null;
    }

    @Override
    public Comment editComment(Long id, Comment comment) {
        return null;
    }

    @Override
    public boolean deleteComment(Long id) {
        return false;
    }

    @Override
    public Comment getComment(Long id) {
        return null;
    }

    @Override
    public List<Comment> getNewsComments(News news) {
        return List.of();
    }
}
