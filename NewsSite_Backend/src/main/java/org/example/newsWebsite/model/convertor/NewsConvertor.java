package org.example.newsWebsite.model.convertor;

import org.example.newsWebsite.model.News;
import org.example.newsWebsite.model.dto.NewsDto;
import org.example.newsWebsite.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("NewsConvertor")
public class NewsConvertor implements PrimitiveConvertor<News, NewsDto> {
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Override
    public NewsDto modelToDto(News model) {
        return new NewsDto(
                model.getId(),
                model.getTitle(),
                model.getCategory(),
                model.getViewNumber(),
                model.getDate(),
                model.getVerification(),
                model.getPhotoPath(),
                model.getAuthor().getId()
        );
    }

    @Override
    public News dtoToModel(NewsDto dto) {
        return new News(
                dto.getId(),
                dto.getTitle(),
                dto.getCategory(),
                dto.getViewNumber(),
                dto.getDate(),
                dto.getVerification(),
                dto.getPhotoPath(),
                userService.getUser(dto.getAuthor())
        );
    }
}
