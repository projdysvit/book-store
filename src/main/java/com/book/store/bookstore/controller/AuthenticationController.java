package com.book.store.bookstore.controller;

import com.book.store.bookstore.dto.request.user.UserLoginRequestDto;
import com.book.store.bookstore.dto.request.user.UserRegistrationRequestDto;
import com.book.store.bookstore.dto.response.user.UserLoginResponseDto;
import com.book.store.bookstore.dto.response.user.UserResponseDto;
import com.book.store.bookstore.service.UserService;
import com.book.store.bookstore.service.security.AuthenticationService;
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
@Tag(name = "Authentication management", description = "Endpoints for auth management")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;
    
    @PostMapping("/registration")
    @Operation(summary = "Register a new user")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.create(requestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "Log in into account")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
