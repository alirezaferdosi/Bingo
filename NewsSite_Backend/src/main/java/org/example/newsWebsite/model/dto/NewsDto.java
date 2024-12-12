package org.example.newsWebsite.model.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class NewsDto {
    private Long id;

    private String title;

    private String newsPath;

    private String category;

    private Integer viewNumber;

    private LocalDate date;

    private String photoPath;

    private Boolean verification;

    private Long author;

    public NewsDto(Long id,
                   String title,
                   String newsPath,
                   String category,
                   Integer viewNumber,
                   LocalDate date,
                   String photoPath,
                   Boolean verification,
                   Long author) {
        this.id = id;
        this.title = title;
        this.newsPath = newsPath;
        this.category = category;
        this.viewNumber = viewNumber;
        this.date = date;
        this.photoPath = photoPath;
        this.verification = verification;
        this.author = author;
    }
}
