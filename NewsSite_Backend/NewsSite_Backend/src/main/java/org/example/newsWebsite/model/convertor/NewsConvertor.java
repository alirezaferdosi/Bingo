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
    public NewsDto modedToDto(News model) {
        return new NewsDto(
                model.getId(),
                model.getTitle(),
                model.getNewsPath(),
                model.getCategory(),
                model.getViewNumber(),
                model.getDate(),
                model.getPhotoPath(),
                model.getVerification(),
                model.getAuthor().getId()
        );
    }

    @Override
    public News dtoToModed(NewsDto dto) {
        return new News(
                dto.getId(),
                dto.getTitle(),
                dto.getNewsPath(),
                dto.getCategory(),
                dto.getViewNumber(),
                dto.getDate(),
                dto.getPhotoPath(),
                dto.getVerification(),
                userService.getUser(dto.getAuthor())
        );
    }
}