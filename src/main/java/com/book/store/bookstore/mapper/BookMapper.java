package com.book.store.bookstore.mapper;

import com.book.store.bookstore.config.MapperConfig;
import com.book.store.bookstore.dto.request.book.BookRequestDto;
import com.book.store.bookstore.dto.response.book.BookResponseDto;
import com.book.store.bookstore.dto.response.book.BookWithoutCategoriesResponseDto;
import com.book.store.bookstore.model.Book;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookResponseDto toDto(Book book);

    BookWithoutCategoriesResponseDto toDtoWithoutCategories(Book book);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "isDeleted", ignore = true)
    })
    Book toModel(BookRequestDto bookDto);

    @AfterMapping
    default void setCategories(@MappingTarget BookResponseDto bookDto, Book book) {
        bookDto.setCategoryIds(
                book.getCategories()
                    .stream()
                    .map(category -> category.getId())
                    .toList()
        );
    }

    @Named("bookFromId")
    default Book bookFromId(Long id) {
        return Book.builder()
                    .id(id)
                    .build();
    }
}
