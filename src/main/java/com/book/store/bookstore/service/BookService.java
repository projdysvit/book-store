package com.book.store.bookstore.service;

import com.book.store.bookstore.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    Book update(Long id, Book book);

    void deleteById(Long id);

    List<Book> findAll();

    Book findById(Long id);
}
