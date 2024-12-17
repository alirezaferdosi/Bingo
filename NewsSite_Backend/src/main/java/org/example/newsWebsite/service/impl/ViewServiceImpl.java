package org.example.newsWebsite.service.impl;

import org.example.newsWebsite.model.View;
import org.example.newsWebsite.model.News;
import org.example.newsWebsite.service.api.ViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewServiceImpl implements ViewService {
    @Override
    public View addComment(View comment) {
        return null;
    }

    @Override
    public View editComment(Long id, View comment) {
        return null;
    }

    @Override
    public boolean deleteComment(Long id) {
        return false;
    }

    @Override
    public View getComment(Long id) {
        return null;
    }

    @Override
    public List<View> getNewsComments(News news) {
        return List.of();
    }
}
