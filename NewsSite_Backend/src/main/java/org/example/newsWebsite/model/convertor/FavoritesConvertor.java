package org.example.newsWebsite.model.convertor;

import org.example.newsWebsite.model.dto.FavoritesDto;
import org.springframework.stereotype.Component;

@Component("favoritesConvertor")
public class FavoritesConvertor implements PrimitiveConvertor<Byte, FavoritesDto>{
    @Override
    public FavoritesDto modelToDto(Byte model) {
        return new FavoritesDto(
                (model & 0b00010000) == 0b00010000,
                (model & 0b00001000) == 0b00001000,
                (model & 0b00000100) == 0b00000100,
                (model & 0b00000010) == 0b00000010,
                (model & 0b00000001) == 0b00000001
                );
    }

    @Override
    public Byte dtoToModel(FavoritesDto dto) {
        return (byte) ((dto.getSports() ? 1 : 0) << 4 |
                      (dto.getHealth() ? 1 : 0) << 3 |
                      (dto.getPolitics() ? 1 : 0) << 2 |
                      (dto.getEconomic() ? 1 : 0) << 1 |
                      (dto.getTechnology() ? 1 : 0));
    }
}
