package com.book.store.bookstore.exception;

public class ShoppingCartForbiddenException extends RuntimeException {
    public ShoppingCartForbiddenException(String message) {
        super(message);
    }
}
