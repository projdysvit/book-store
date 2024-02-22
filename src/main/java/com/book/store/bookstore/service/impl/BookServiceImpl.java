package com.book.store.bookstore.service.impl;

import com.book.store.bookstore.dto.mapper.RequestDtoMapper;
import com.book.store.bookstore.dto.request.book.CreateBookRequestDto;
import com.book.store.bookstore.exception.EntityNotFoundException;
import com.book.store.bookstore.model.Book;
import com.book.store.bookstore.repository.BookRepository;
import com.book.store.bookstore.service.BookService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private RequestDtoMapper<CreateBookRequestDto, Book> requestDtoMapper;

    public BookServiceImpl(BookRepository bookRepository,
                            RequestDtoMapper<CreateBookRequestDto, Book> requestDtoMapper) {
        this.bookRepository = bookRepository;
        this.requestDtoMapper = requestDtoMapper;
    }

    @Override
    public Book save(CreateBookRequestDto dto) {
        Book book = requestDtoMapper.toModel(dto);
        return bookRepository.save(book);
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
