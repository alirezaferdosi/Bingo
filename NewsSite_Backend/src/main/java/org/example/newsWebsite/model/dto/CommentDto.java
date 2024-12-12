package org.example.newsWebsite.model.dto;

import lombok.Getter;

@Getter
public class CommentDto {
    private Long id;

    private Long user;

    private Long news;

    private String comment;

    public CommentDto(Long id,
                      Long user,
                      Long news,
                      String comment) {
        this.id = id;
        this.user = user;
        this.news = news;
        this.comment = comment;
    }
}
