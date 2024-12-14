package org.example.newsWebsite.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@ToString
@Getter @Setter
public class NewsDto {
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9.-_@#()\\/]+", message = "Title must be at least 1 characters long and contain only letters, digits, 'a-z' 'A-Z' '.' '@' '#' '_' '-' '/' ''\\")
    private String title;

    private String newsPath;

    @Pattern(regexp = "^[a-zA-Z]+", message = "Category must be contain letter")
    private String category;

    private Integer viewNumber;

    private String date;

    private String photoPath;

    private Boolean verification;

    private Long author;

    public NewsDto(Long id,
                   @NonNull String title,
                   @NonNull String newsPath,
                   @NonNull String category,
                   Integer viewNumber,
                   String date,
                   String photoPath,
                   Boolean verification,
                   @NonNull Long author) {
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
