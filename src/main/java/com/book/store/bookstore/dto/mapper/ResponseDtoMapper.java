package com.book.store.bookstore.dto.mapper;

public interface ResponseDtoMapper<D, M> {
    public D toDto(M model);
}
