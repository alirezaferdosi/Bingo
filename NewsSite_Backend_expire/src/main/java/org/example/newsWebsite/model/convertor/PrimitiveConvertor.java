package org.example.newsWebsite.model.convertor;

public interface PrimitiveConvertor<M, D> {
    D modelToDto(M model);

    M dtoToModel(D dto);
}
