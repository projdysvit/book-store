package com.book.store.bookstore.service.impl;

import com.book.store.bookstore.dto.request.cartitem.CartItemRequestDto;
import com.book.store.bookstore.dto.response.cartitem.CartItemResponseDto;
import com.book.store.bookstore.dto.response.shoppingcart.ShoppingCartResponseDto;
import com.book.store.bookstore.exception.EntityNotFoundException;
import com.book.store.bookstore.exception.ShoppingCartForbiddenException;
import com.book.store.bookstore.mapper.CartItemMapper;
import com.book.store.bookstore.mapper.ShoppingCartMapper;
import com.book.store.bookstore.model.CartItem;
import com.book.store.bookstore.model.ShoppingCart;
import com.book.store.bookstore.model.User;
import com.book.store.bookstore.repository.CartItemRepository;
import com.book.store.bookstore.repository.ShoppingCartRepository;
import com.book.store.bookstore.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;
    private final CartItemMapper cartItemMapper;

    @Override
    public ShoppingCartResponseDto getByUserId(Long userId) {
        return shoppingCartMapper.toDto(getShoppingCartByUserId(userId));
    }

    @Override
    public void create(User user) {
        shoppingCartRepository.save(ShoppingCart.builder().user(user).build());
    }

    @Override
    @Transactional
    public ShoppingCartResponseDto addBook(Long userId, CartItemRequestDto requestDto) {
        CartItem newCartItem = cartItemMapper.toModel(requestDto);
        newCartItem.setShoppingCart(getShoppingCartByUserId(userId));

        cartItemRepository.save(newCartItem);

        return shoppingCartMapper.toDto(getShoppingCartByUserId(userId));
    }

    @Override
    @Transactional
    public CartItemResponseDto updateCartItem(
            Long userId,
            Long cartItemId, 
            CartItemRequestDto requestDto
    ) {
        CartItem cartItem = getCartItemById(userId, cartItemId);

        cartItem.setQuantity(requestDto.quantity());
        
        return cartItemMapper.toDto(cartItemRepository.save(cartItem));
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) {
        cartItemRepository.deleteById(getCartItemById(userId, cartItemId).getId());
    }

    private ShoppingCart getShoppingCartByUserId(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId).orElseThrow(
                () -> new EntityNotFoundException("Shopping Cart with id '" + userId 
                                                    + "' not found")
        );

        return shoppingCart;
    }

    private CartItem getCartItemById(Long userId, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException("Cart Item with id '" + cartItemId 
                                                    + "' not found")
        );

        if (!cartItem.getShoppingCart().getId().equals(userId)) {
            throw new ShoppingCartForbiddenException("Not your's shopping cart");
        }

        return cartItem;
    }
}
