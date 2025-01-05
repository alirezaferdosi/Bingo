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

    @Pattern(regexp = "^[a-zA-Z]+", message = "Category must be contain letter")
    private String category;

    private String content;

    private Integer viewNumber;

    private String date;

    private Boolean verification;

    private String author;

    private String photoPath;

    public NewsDto(Long id,
                   @NonNull String title,
                   @NonNull String category,
                   @NonNull String content,
                   Integer viewNumber,
                   String date,
                   Boolean verification,
                   String photoPath,
                   @NonNull String author) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.content = content;
        this.viewNumber = viewNumber;
        this.date = date;
        this.verification = verification;
        this.author = author;
        this.photoPath = photoPath;
    }
}
