package com.book.store.bookstore.mapper;

import com.book.store.bookstore.config.MapperConfig;
import com.book.store.bookstore.dto.response.shoppingcart.ShoppingCartResponseDto;
import com.book.store.bookstore.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    @Mappings({
            @Mapping(target = "userId", source = "user.id"),
            @Mapping(
                    target = "cartItemIds",
                    source = "cartItems",
                    qualifiedByName = "getCartItemIds"
            )
    })
    ShoppingCartResponseDto toDto(ShoppingCart shoppingCart);
}
