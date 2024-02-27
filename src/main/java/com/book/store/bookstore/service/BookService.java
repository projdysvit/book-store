package com.book.store.bookstore.service;

import com.book.store.bookstore.dto.request.book.BookRequestDto;
import com.book.store.bookstore.dto.request.book.BookSearchParameters;
import com.book.store.bookstore.dto.response.book.BookDto;
import java.util.List;

public interface BookService {
    BookDto create(BookRequestDto bookDto);

    BookDto update(Long id, BookRequestDto bookDto);

    void deleteById(Long id);

    List<BookDto> findAll();

    BookDto findById(Long id);

    List<BookDto> search(BookSearchParameters params);
}
