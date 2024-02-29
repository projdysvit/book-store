package com.book.store.bookstore.util.validation.user;

import com.book.store.bookstore.dto.request.user.UserRegistrationRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PasswordMatchValidator 
        implements ConstraintValidator<PasswordMatch, UserRegistrationRequestDto> {
    @Override
    public boolean isValid(UserRegistrationRequestDto requestDto, 
                            ConstraintValidatorContext context) {
        return Objects.equals(requestDto.getPassword(), requestDto.getRepeatPassword());
    }
}
