package com.book.store.bookstore.mapper;

import com.book.store.bookstore.config.MapperConfig;
import com.book.store.bookstore.dto.request.book.BookRequestDto;
import com.book.store.bookstore.dto.response.book.BookDto;
import com.book.store.bookstore.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(BookRequestDto bookDto);
}
