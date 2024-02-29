package com.book.store.bookstore.dto.request.user;

import com.book.store.bookstore.util.validation.user.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordMatch
public record UserRegistrationRequestDto(
    @Email
    @NotBlank
    String email,
    @NotBlank
    @Size(min = 8)
    String password,
    @NotBlank
    @Size(min = 8)
    String repeatPassword,
    @NotBlank
    String firstName,
    @NotBlank
    String lastName,
    @NotBlank
    String shippingAddress
) {
}
