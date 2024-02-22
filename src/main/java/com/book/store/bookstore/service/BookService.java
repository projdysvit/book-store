package com.book.store.bookstore.service;

import com.book.store.bookstore.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
