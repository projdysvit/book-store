package com.book.store.bookstore.dto.request.book;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CategoryIdsDto(
        @NotNull
        @Positive
        Long id
) {
}
