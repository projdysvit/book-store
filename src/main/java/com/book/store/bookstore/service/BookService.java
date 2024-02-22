package com.book.store.bookstore.service;

import com.book.store.bookstore.dto.request.book.CreateBookRequestDto;
import com.book.store.bookstore.model.Book;
import java.util.List;

public interface BookService {
    Book save(CreateBookRequestDto dto);

    List<Book> findAll();

    Book findById(Long id);
}
