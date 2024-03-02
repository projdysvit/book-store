package com.book.store.bookstore.service;

import com.book.store.bookstore.dto.request.book.BookRequestDto;
import com.book.store.bookstore.dto.request.book.BookSearchParameters;
import com.book.store.bookstore.dto.response.book.BookResponseDto;
import com.book.store.bookstore.dto.response.book.BookWithoutCategoriesResponseDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto create(BookRequestDto bookDto);

    BookResponseDto update(Long id, BookRequestDto bookDto);

    void deleteById(Long id);

    List<BookResponseDto> findAll(Pageable pageable);

    List<BookWithoutCategoriesResponseDto> getAllByCategoryId(Long id, Pageable pageable);

    BookResponseDto findById(Long id);

    List<BookResponseDto> search(Pageable pageable, BookSearchParameters params);
}
