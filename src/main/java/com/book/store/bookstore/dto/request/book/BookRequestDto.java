package com.book.store.bookstore.dto.request.book;

import java.math.BigDecimal;

public record BookRequestDto(
        String title,
        String author,
        String isbn,
        BigDecimal price,
        String description,
        String coverImage
) {
}
