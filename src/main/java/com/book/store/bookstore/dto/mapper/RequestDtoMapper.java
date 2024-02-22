package com.book.store.bookstore.dto.mapper;

public interface RequestDtoMapper<D, M> {
    public M toModel(D dto);
}
