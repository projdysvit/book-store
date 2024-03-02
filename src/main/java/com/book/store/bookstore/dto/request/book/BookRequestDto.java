package com.book.store.bookstore.dto.request.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public record BookRequestDto(
        @NotBlank
        String title,
        @NotBlank
        String author,
        @NotBlank
        String isbn,
        @NotNull
        @Min(0)
        BigDecimal price,
        @NotBlank
        String description,
        @NotBlank
        String coverImage,
        List<CategoryIdsDto> categoryIds
) {
}
