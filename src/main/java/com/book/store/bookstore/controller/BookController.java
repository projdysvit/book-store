package com.book.store.bookstore.controller;

import com.book.store.bookstore.dto.request.book.BookRequestDto;
import com.book.store.bookstore.dto.request.book.BookSearchParameters;
import com.book.store.bookstore.dto.response.book.BookDto;
import com.book.store.bookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
@RequiredArgsConstructor
@Tag(name = "Book managment", description = "Endpoints for managing books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    @Operation(summary = "Create a new book")
    public BookDto createBook(@RequestBody @Valid BookRequestDto bookDto) {
        return bookService.create(bookDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an exist book")
    public BookDto update(@PathVariable Long id, @RequestBody BookRequestDto bookDto) {
        return bookService.update(id, bookDto);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Soft delete an exist book")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @GetMapping
    @Operation(
            summary = "Get all books",
            description = "Get all non-deleted books with pagination"
    )
    public List<BookDto> getAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get a book by id")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @GetMapping("/search")
    @Operation(
            summary = "Get all books",
            description = "Get all non-deleted books with pagination and search params"
    )
    public List<BookDto> search(Pageable pageable, BookSearchParameters params) {
        return bookService.search(pageable, params);
    }
}
