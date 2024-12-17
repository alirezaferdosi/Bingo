package org.example.newsWebsite.model.dto;

import io.micrometer.common.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter @Setter
public class ViewDto {
    private Long id;

    private Long user;

    private Long news;


    public ViewDto(Long id,
                   @NonNull Long user,
                   @NonNull Long news) {
        this.id = id;
        this.user = user;
        this.news = news;
    }
}
