package com.book.store.bookstore.service;

import com.book.store.bookstore.dto.request.user.UserRegistrationRequestDto;
import com.book.store.bookstore.dto.response.user.UserResponseDto;

public interface UserService {
    UserResponseDto create(UserRegistrationRequestDto requestDto);
}
