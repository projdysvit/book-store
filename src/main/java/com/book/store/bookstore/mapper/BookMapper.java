package com.book.store.bookstore.mapper;

import com.book.store.bookstore.config.MapperConfig;
import com.book.store.bookstore.dto.request.book.BookRequestDto;
import com.book.store.bookstore.dto.response.book.BookResponseDto;
import com.book.store.bookstore.dto.response.book.BookWithoutCategoriesResponseDto;
import com.book.store.bookstore.model.Book;
import com.book.store.bookstore.model.Category;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    @Mapping(
            target = "categoryIds",
            source = "categories",
            qualifiedByName = "getCategoryIds"
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

    @Named("getCategoryIds")
    default List<Long> getCategoryIds(Set<Category> categories) {
        return categories.stream()
                            .map(Category::getId)
                            .toList();
    }
}
