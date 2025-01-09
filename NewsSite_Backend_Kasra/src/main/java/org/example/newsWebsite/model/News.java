package org.example.newsWebsite.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News implements Comparable<News> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "content", length = 100000)
    private String content;

    @Column(name = "view_number", nullable = false)
    private Integer viewNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private String date;

    @Column(name = "verification")
    private Boolean verification;

    @ManyToOne(targetEntity = User.class/*, cascade = CascadeType.ALL */)
    @JoinColumn(name = "author", nullable = false)
    private User author;

    @Column(name = "photoPath", nullable = false)
    private String photoPath;


    @Override
    public int compareTo(News o) {
        return 0;
    }
}







