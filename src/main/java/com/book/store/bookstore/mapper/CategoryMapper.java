package com.book.store.bookstore.mapper;

import com.book.store.bookstore.config.MapperConfig;
import com.book.store.bookstore.dto.request.category.CategoryRequestDto;
import com.book.store.bookstore.dto.response.category.CategoryResponseDto;
import com.book.store.bookstore.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryResponseDto toDto(Category category);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "deleted", ignore = true)
    })
    Category toModel(CategoryRequestDto categoryRequestDto);
}
