package com.book.store.bookstore.service.impl;

import com.book.store.bookstore.dto.request.category.CategoryRequestDto;
import com.book.store.bookstore.dto.response.category.CategoryResponseDto;
import com.book.store.bookstore.mapper.CategoryMapper;
import com.book.store.bookstore.model.Category;
import com.book.store.bookstore.repository.CategoryRepository;
import com.book.store.bookstore.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                                    .stream()
                                    .map(categoryMapper::toDto)
                                    .toList();
    }
    
    @Override
    public CategoryResponseDto getById(Long id) {
        return categoryMapper.toDto(
            categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category with id '" + id + "' not found")
            )
        );
    }

    @Override
    public CategoryResponseDto create(CategoryRequestDto requestDto) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toModel(requestDto)));
    }

    @Override
    public CategoryResponseDto update(Long id, CategoryRequestDto requestDto) {
        if (categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id '" + id + "' not found");
        }

        Category newCategory = categoryMapper.toModel(requestDto);
        newCategory.setId(id);

        return categoryMapper.toDto(categoryRepository.save(newCategory));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
