package org.example.newsWebsite.model.convertor;

public interface PrimitiveConvertor<M, D> {
    D modedToDto(M model);

    M dtoToModed(D model);
}
