package com.book.store.bookstore.service;

import com.book.store.bookstore.dto.request.cartitem.CartItemRequestDto;
import com.book.store.bookstore.dto.response.cartitem.CartItemResponseDto;
import com.book.store.bookstore.dto.response.shoppingcart.ShoppingCartResponseDto;
import com.book.store.bookstore.model.User;

public interface ShoppingCartService {
    ShoppingCartResponseDto getByUserId(Long userId);

    void create(User user);

    ShoppingCartResponseDto addBook(Long userId, CartItemRequestDto requestDto);

    CartItemResponseDto updateCartItem(
            Long userId, 
            Long cartItemId, 
            CartItemRequestDto requestDto
    );

    void removeCartItem(Long userId, Long cartItemId);
}
