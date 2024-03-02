package com.book.store.bookstore.dto.response.book;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public record BookResponseDto(
        Long id,
        String title,
        String author,
        String isbn,
        BigDecimal price,
        String description,
        String coverImage,
        List<Long> categoryIds
) {
    public BookResponseDto(
            Long id,
            String title,
            String author,
            String isbn,
            BigDecimal price,
            String description,
            String coverImage,
            List<Long> categoryIds
    ) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.description = description;
        this.coverImage = coverImage;
        this.categoryIds = new LinkedList<>();
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds.addAll(categoryIds);
    }
}
