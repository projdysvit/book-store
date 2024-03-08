package com.book.store.bookstore.service;

import com.book.store.bookstore.dto.response.orderitem.OrderItemResponseDto;
import java.util.List;

public interface OrderItemService {
    List<OrderItemResponseDto> getAllByOrderId(Long orderId);

    OrderItemResponseDto getByIdAndOrderId(Long id, Long orderId);
}
