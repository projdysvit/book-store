package com.book.store.bookstore.service.impl;

import com.book.store.bookstore.exception.EntityNotFoundException;
import com.book.store.bookstore.model.Book;
import com.book.store.bookstore.repository.BookRepository;
import com.book.store.bookstore.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book newBook) {
        findById(id);
        newBook.setId(id);
        return save(newBook);
    }

    @Override
    public void deleteById(Long id) {
        Book book = findById(id);
        book.setDeleted(true);
        save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Book with id " + id + " not found.")
        );
    }
}
