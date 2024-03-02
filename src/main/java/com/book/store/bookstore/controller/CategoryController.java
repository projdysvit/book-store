package com.book.store.bookstore.controller;

import com.book.store.bookstore.dto.request.category.CategoryRequestDto;
import com.book.store.bookstore.dto.response.book.BookWithoutCategoriesResponseDto;
import com.book.store.bookstore.dto.response.category.CategoryResponseDto;
import com.book.store.bookstore.service.BookService;
import com.book.store.bookstore.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
@RequiredArgsConstructor
@Tag(name = "Category management", description = "Endpoints for managing categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Create a new category")
    public CategoryResponseDto create(@RequestBody @Valid CategoryRequestDto requestDto) {
        return categoryService.create(requestDto);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Update an exists category by id")
    public CategoryResponseDto update(
            @PathVariable Long id,
            @RequestBody @Valid CategoryRequestDto requestDto
    ) {
        return categoryService.update(id, requestDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get all categories")
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get an exists category by id")
    public CategoryResponseDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @GetMapping("/{id}/books")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get all books by category id")
    public List<BookWithoutCategoriesResponseDto> getAllBooksByCategoryId(
            @PathVariable Long id,
            Pageable pageable
    ) {
        return bookService.getAllByCategoryId(id, pageable);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete an exists category by id")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
