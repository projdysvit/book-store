package com.book.store.bookstore.service;

import com.book.store.bookstore.dto.request.book.BookRequestDto;
import com.book.store.bookstore.dto.request.book.BookSearchParameters;
import com.book.store.bookstore.dto.response.book.BookDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto create(BookRequestDto bookDto);

    BookDto update(Long id, BookRequestDto bookDto);

    void deleteById(Long id);

    List<BookDto> findAll(Pageable pageable);

    BookDto findById(Long id);

    List<BookDto> search(Pageable pageable, BookSearchParameters params);
}
