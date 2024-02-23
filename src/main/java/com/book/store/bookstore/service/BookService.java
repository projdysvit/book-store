package com.book.store.bookstore.service;

import com.book.store.bookstore.dto.request.book.CreateBookRequestDto;
import com.book.store.bookstore.dto.response.book.BookDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    List<BookDto> findAll();

    BookDto findById(Long id);
}
