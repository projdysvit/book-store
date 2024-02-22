package com.book.store.bookstore.controller;

import com.book.store.bookstore.dto.mapper.ResponseDtoMapper;
import com.book.store.bookstore.dto.request.book.CreateBookRequestDto;
import com.book.store.bookstore.dto.response.book.BookDto;
import com.book.store.bookstore.model.Book;
import com.book.store.bookstore.service.BookService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private BookService bookService;
    private ResponseDtoMapper<BookDto, Book> responseDtoMapper;

    public BookController(BookService bookService, 
                            ResponseDtoMapper<BookDto, Book> responseDtoMapper) {
        this.bookService = bookService;
        this.responseDtoMapper = responseDtoMapper;
    }

    @PostMapping("/api/books")
    public BookDto createBook(@RequestBody CreateBookRequestDto bookDto) {
        return responseDtoMapper.toDto(bookService.save(bookDto));
    }

    @GetMapping("/api/books")
    public List<BookDto> getAll() {
        return bookService.findAll()
                            .stream()
                            .map(book -> responseDtoMapper.toDto(book))
                            .toList();
    }
    
    @GetMapping("/api/books/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return responseDtoMapper.toDto(bookService.findById(id));
    }
}
