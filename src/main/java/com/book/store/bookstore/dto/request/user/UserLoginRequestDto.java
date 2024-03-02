package com.book.store.bookstore.dto.request.user;

public record UserLoginRequestDto(
        String email,
        String password
) {
}
