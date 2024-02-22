package com.book.store.bookstore.service.impl;

import com.book.store.bookstore.model.Book;
import com.book.store.bookstore.repository.BookRepository;
import com.book.store.bookstore.service.BookService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
	private BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
}
