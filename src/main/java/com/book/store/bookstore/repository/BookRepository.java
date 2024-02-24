package com.book.store.bookstore.repository;

import com.book.store.bookstore.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    public Book save(Book book);

    public List<Book> findAll();

    public Optional<Book> findById(Long id);
}
