package com.book.store.bookstore.dto.request.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
