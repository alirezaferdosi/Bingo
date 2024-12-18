package org.example.newsWebsite.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
public class FavoritesDto {
    private Boolean sports;

    private Boolean health;

    private Boolean politics;

    private Boolean economic;

    private Boolean technology;

    public FavoritesDto(@NonNull Boolean sports,
                        @NonNull Boolean health,
                        @NonNull Boolean politics,
                        @NonNull Boolean economic,
                        @NonNull Boolean technology) {
        this.sports = sports;
        this.health = health;
        this.politics = politics;
        this.economic = economic;
        this.technology = technology;
    }
}
