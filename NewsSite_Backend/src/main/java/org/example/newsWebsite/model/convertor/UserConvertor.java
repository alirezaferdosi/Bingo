package org.example.newsWebsite.model.convertor;

import org.example.newsWebsite.model.User;
import org.example.newsWebsite.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component("UserConvertor")
public class UserConvertor implements PrimitiveConvertor<User, UserDto>{
    @Override
    public UserDto modedToDto(User model) {
        return new UserDto(
                model.getId(),
                model.getUsername(),
                model.getPhone(),
                model.getEmail(),
                model.getPassword()
        );
    }

    @Override
    public User dtoToModed(UserDto dto) {
        return new User(
                dto.getId(),
                dto.getUsername(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getPassword()
        );
    }
}
