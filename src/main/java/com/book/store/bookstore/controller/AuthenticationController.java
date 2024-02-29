package com.book.store.bookstore.controller;

import com.book.store.bookstore.dto.request.user.UserRegistrationRequestDto;
import com.book.store.bookstore.dto.response.user.UserResponseDto;
import com.book.store.bookstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication managment", description = "Endpoints for auth managment")
public class AuthenticationController {
    private final UserService userService;
    
    @PostMapping("/registration")
    @Operation(summary = "Register a new user")
    public UserResponseDto regiter(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.create(requestDto);
    }
}
