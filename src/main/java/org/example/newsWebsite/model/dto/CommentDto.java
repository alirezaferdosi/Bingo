package org.example.newsWebsite.model.dto;

import io.micrometer.common.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter @Setter
public class CommentDto {
    private Long id;

    private Long user;

    private Long news;

    private String comment;

    public CommentDto(Long id,
                      @NonNull Long user,
                      @NonNull Long news,
                      @NonNull String comment) {
        this.id = id;
        this.user = user;
        this.news = news;
        this.comment = comment;
    }
}
