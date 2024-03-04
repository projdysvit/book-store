package com.book.store.bookstore.mapper;

import com.book.store.bookstore.config.MapperConfig;
import com.book.store.bookstore.dto.request.book.BookRequestDto;
import com.book.store.bookstore.dto.response.book.BookResponseDto;
import com.book.store.bookstore.dto.response.book.BookWithoutCategoriesResponseDto;
import com.book.store.bookstore.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    @Mapping(
            target = "categoryIds",
            expression = "java("
                + "book.getCategories()"
                    + ".stream()"
                    + ".map(category -> category.getId())"
                    + ".toList()"
            + ")"
    )
    BookResponseDto toDto(Book book);

    BookWithoutCategoriesResponseDto toDtoWithoutCategories(Book book);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "isDeleted", ignore = true)
    })
    Book toModel(BookRequestDto bookDto);

    @Named("bookFromId")
    default Book bookFromId(Long id) {
        return Book.builder()
                    .id(id)
                    .build();
    }
}
