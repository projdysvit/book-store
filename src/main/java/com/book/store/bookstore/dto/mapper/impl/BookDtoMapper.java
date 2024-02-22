package com.book.store.bookstore.dto.mapper.impl;

import com.book.store.bookstore.dto.mapper.RequestDtoMapper;
import com.book.store.bookstore.dto.mapper.ResponseDtoMapper;
import com.book.store.bookstore.dto.request.book.CreateBookRequestDto;
import com.book.store.bookstore.dto.response.book.BookDto;
import com.book.store.bookstore.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDtoMapper implements RequestDtoMapper<CreateBookRequestDto, Book>,
                                        ResponseDtoMapper<BookDto, Book> {
    @Override
    public BookDto toDto(Book model) {
        return BookDto.builder()
                        .id(model.getId())
                        .title(model.getTitle())
                        .author(model.getAuthor())
                        .isbn(model.getIsbn())
                        .price(model.getPrice())
                        .description(model.getDescription())
                        .coverImage(model.getCoverImage())
                        .build();
    }

    @Override
    public Book toModel(CreateBookRequestDto dto) {
        return Book.builder()
                    .title(dto.getTitle())
                    .author(dto.getAuthor())
                    .isbn(dto.getIsbn())
                    .price(dto.getPrice())
                    .description(dto.getDescription())
                    .coverImage(dto.getCoverImage())
                    .build();
    }
}
