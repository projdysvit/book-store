package com.book.store.bookstore.mapper;

import com.book.store.bookstore.config.MapperConfig;
import com.book.store.bookstore.dto.response.order.OrderResponseDto;
import com.book.store.bookstore.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toDto(Order order);
}
