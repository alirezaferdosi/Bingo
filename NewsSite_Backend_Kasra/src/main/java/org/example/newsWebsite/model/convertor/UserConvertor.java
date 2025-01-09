package org.example.newsWebsite.model.convertor;

import org.example.newsWebsite.model.User;
import org.example.newsWebsite.model.dto.FavoritesDto;
import org.example.newsWebsite.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("UserConvertor")
public class UserConvertor implements PrimitiveConvertor<User, UserDto>{
    @Autowired
    @Qualifier("favoritesConvertor")
    private PrimitiveConvertor<Byte, FavoritesDto> convertor;

    @Override
    public UserDto modelToDto(User model) {
        return new UserDto(
                model.getId(),
                model.getUsername(),
                model.getPhone(),
                model.getEmail(),
                model.getPassword(),
                convertor.modelToDto(model.getFavorites()),
                model.getPhotoPath()
        );
    }

    @Override
    public User dtoToModel(UserDto dto) {
        return new User(
                dto.getId(),
                dto.getUsername(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getPhotoPath(),
                convertor.dtoToModel(dto.getFavorites())
        );
    }
}
