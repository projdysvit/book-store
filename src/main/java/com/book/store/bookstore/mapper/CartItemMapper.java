package com.book.store.bookstore.mapper;

import com.book.store.bookstore.config.MapperConfig;
import com.book.store.bookstore.dto.request.cartitem.CartItemRequestDto;
import com.book.store.bookstore.dto.response.cartitem.CartItemResponseDto;
import com.book.store.bookstore.model.CartItem;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = BookMapper.class)
public interface CartItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    CartItemResponseDto toDto(CartItem cartItem);

    @Mapping(target = "book", source = "bookId", qualifiedByName = "bookFromId")
    CartItem toModel(CartItemRequestDto requestDto);

    @Named("getCartItemIds")
    default List<CartItemResponseDto> getCartItemIds(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::toDto)
                .toList();
    }
}
