package com.book.store.bookstore.dto.response.book;

import java.math.BigDecimal;
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
}
