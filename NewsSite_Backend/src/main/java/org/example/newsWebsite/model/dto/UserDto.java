package org.example.newsWebsite.model.dto;

import lombok.Getter;

@Getter
public class UserDto {
    private Long id;

    private String username;

    private String phone;

    private String email;

    private String password;

    private String photoPath;

    public UserDto(Long id,
                   String username,
                   String phone,
                   String email,
                   String password,
                   String photoPath) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.photoPath = photoPath;
    }
}
