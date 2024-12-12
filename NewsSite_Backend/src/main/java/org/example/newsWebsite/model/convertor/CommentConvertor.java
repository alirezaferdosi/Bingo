package org.example.newsWebsite.model.convertor;

import org.example.newsWebsite.model.Comment;
import org.example.newsWebsite.model.dto.CommentDto;
import org.example.newsWebsite.service.api.NewsService;
import org.example.newsWebsite.service.api.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("CommentCponvertor")
public class CommentConvertor implements PrimitiveConvertor<Comment, CommentDto>{
    @Qualifier("userService")
    private UserService userService;

    @Qualifier("newsService")
    private NewsService newsService;

    @Override
    public CommentDto modedToDto(Comment model) {
        return new CommentDto(
                model.getId(),
                model.getUser().getId(),
                model.getNews().getId(),
                model.getComment()
        );
    }

    @Override
    public Comment dtoToModed(CommentDto model) {
        return new Comment(
                model.getId(),
                userService.getUser(model.getUser()),
                newsService.getNews(model.getNews()),
                model.getComment()
        );
    }
}
