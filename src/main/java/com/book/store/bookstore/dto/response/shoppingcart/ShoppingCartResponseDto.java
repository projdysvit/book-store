package com.book.store.bookstore.dto.response.shoppingcart;

import com.book.store.bookstore.dto.response.cartitem.CartItemResponseDto;
import java.util.List;

public record ShoppingCartResponseDto(
        Long id,
        Long userId,
        List<CartItemResponseDto> cartItemIds
) {
}
