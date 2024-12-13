package org.example.newsWebsite.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "newsPath", nullable = false)
    private String newsPath;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "viewNumber", nullable = false)
    private Integer viewNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "photoPath")
    private String photoPath;

    @Column(name = "verification")
    private Boolean verification;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author", nullable = false)
    private User author;
}







