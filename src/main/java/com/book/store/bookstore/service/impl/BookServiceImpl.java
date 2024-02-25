package com.book.store.bookstore.service.impl;

import com.book.store.bookstore.dto.request.book.BookRequestDto;
import com.book.store.bookstore.dto.response.book.BookDto;
import com.book.store.bookstore.exception.EntityNotFoundException;
import com.book.store.bookstore.mapper.BookMapper;
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
    private final BookMapper bookMapper;

    @Override
    public BookDto save(BookRequestDto bookDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(bookDto)));
    }

    @Override
    public BookDto update(Long id, BookRequestDto bookDto) {
        if (bookRepository.existsById(id)) {
            Book book = bookMapper.toModel(bookDto);
            book.setId(id);
            return bookMapper.toDto(bookRepository.save(book));
        }

        throw new EntityNotFoundException("Book with id " + id + " not found.");
    }

    @Override
    public void deleteById(Long id) {
        Book book = getByIdOrException(id);
        book.setDeleted(true);
        bookRepository.save(book);
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll()
                                .stream()
                                .map(book -> bookMapper.toDto(book))
                                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(getByIdOrException(id));
    }

    private Book getByIdOrException(Long id) {
        return bookRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Book with id " + id + " not found.")
        );
    }
}
