package com.book.store.bookstore.dto.request.book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CategoryIdsDto(
        @NotNull
        @Min(0)
        Long id
) {
}
