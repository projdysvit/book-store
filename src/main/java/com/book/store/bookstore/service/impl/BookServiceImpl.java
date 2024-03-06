package com.book.store.bookstore.service.impl;

import com.book.store.bookstore.dto.request.book.BookRequestDto;
import com.book.store.bookstore.dto.request.book.BookSearchParameters;
import com.book.store.bookstore.dto.request.book.CategoryIdsDto;
import com.book.store.bookstore.dto.response.book.BookResponseDto;
import com.book.store.bookstore.dto.response.book.BookWithoutCategoriesResponseDto;
import com.book.store.bookstore.exception.EntityNotFoundException;
import com.book.store.bookstore.mapper.BookMapper;
import com.book.store.bookstore.model.Book;
import com.book.store.bookstore.repository.BookRepository;
import com.book.store.bookstore.repository.CategoryRepository;
import com.book.store.bookstore.repository.specification.book.BookSpecificationBuilder;
import com.book.store.bookstore.service.BookService;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder specificationBuilder;

    @Override
    @Transactional
    public BookResponseDto create(BookRequestDto bookDto) {
        Book newBook = bookMapper.toModel(bookDto);
        newBook.setCategories(
            new HashSet<>(
                categoryRepository.findAllById(
                    bookDto.categoryIds()
                            .stream()
                            .map(CategoryIdsDto::id)
                            .collect(Collectors.toSet())
                )
            )
        );

        return bookMapper.toDto(bookRepository.save(newBook));
    }

    @Override
    @Transactional
    public BookResponseDto update(Long id, BookRequestDto bookDto) {
        checkIfExists(id);
        
        Book book = bookMapper.toModel(bookDto);
        book.setId(id);

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        checkIfExists(id);

        bookRepository.deleteById(id);
    }

    @Override
    public List<BookResponseDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                                .stream()
                                .map(bookMapper::toDto)
                                .toList();
    }

    @Override
    public BookResponseDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Book with id " + id + " not found.")
            )
        );
    }

    @Override
    public List<BookResponseDto> search(Pageable pageable, BookSearchParameters params) {
        return bookRepository.findAll(specificationBuilder.build(params), pageable)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookWithoutCategoriesResponseDto> getAllByCategoryId(Long id, Pageable pageable) {
        return bookRepository.findAllByCategoryId(id, pageable)
                                .stream()
                                .map(bookMapper::toDtoWithoutCategories)
                                .toList();
    }

    private void checkIfExists(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book with id " + id + " not found.");
        }
    }
}
