package com.book.store.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectSpecificationKeyException extends RuntimeException {
    public IncorrectSpecificationKeyException(String message) {
        super(message);
    }
}
