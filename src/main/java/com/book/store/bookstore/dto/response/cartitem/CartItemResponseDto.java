package com.book.store.bookstore.dto.response.cartitem;

public record CartItemResponseDto(
        Long id,
        Long bookId,
        int quantity
) {
}
