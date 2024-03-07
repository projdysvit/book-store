package com.book.store.bookstore.controller;

import com.book.store.bookstore.dto.request.cartitem.CartItemRequestDto;
import com.book.store.bookstore.dto.response.cartitem.CartItemResponseDto;
import com.book.store.bookstore.dto.response.shoppingcart.ShoppingCartResponseDto;
import com.book.store.bookstore.model.User;
import com.book.store.bookstore.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cart")
@RequiredArgsConstructor
@Tag(name = "Shopping cart management", description = "Endpoints for managing shopping cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get a shopping cart by authenticated user")
    public ShoppingCartResponseDto getShoppingCart(Authentication authentication) {
        return shoppingCartService.getByUserId(((User) authentication.getPrincipal()).getId());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Add book to shopping cart")
    public ShoppingCartResponseDto addBook(
            Authentication authentication,
            @RequestBody @Valid CartItemRequestDto requestDto
    ) {
        return shoppingCartService.addBook(
            ((User) authentication.getPrincipal()).getId(),
            requestDto
        );
    }

    @PutMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Update a cart item by id")
    public CartItemResponseDto updateCartItem(
            Authentication authentication,
            @PathVariable Long cartItemId,
            @RequestBody @Valid CartItemRequestDto requestDto
    ) {
        return shoppingCartService.updateCartItem(
            ((User) authentication.getPrincipal()).getId(),
            cartItemId, 
            requestDto
        );
    }

    @DeleteMapping("/cart-items/{cartItemId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Remove an exist cart item by id")
    public void removeCartItem(Authentication authentication, @PathVariable Long cartItemId) {
        shoppingCartService.removeCartItem(
                ((User) authentication.getPrincipal()).getId(),
                cartItemId
        );
    }
}
