package com.book.store.bookstore.service;

import com.book.store.bookstore.dto.request.order.OrderRequestDto;
import com.book.store.bookstore.dto.request.order.OrderUpdateRequestDto;
import com.book.store.bookstore.dto.response.order.OrderResponseDto;
import java.util.List;

public interface OrderService {
    OrderResponseDto placeOrder(Long userId, OrderRequestDto requestDto);

    List<OrderResponseDto> getAllByUserId(Long userId);

    OrderResponseDto updateStatus(Long orderId, OrderUpdateRequestDto requestDto);
}
