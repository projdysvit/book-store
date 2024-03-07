package com.book.store.bookstore.service.impl;

import com.book.store.bookstore.dto.response.orderitem.OrderItemResponseDto;
import com.book.store.bookstore.mapper.OrderItemMapper;
import com.book.store.bookstore.repository.OrderItemRepository;
import com.book.store.bookstore.service.OrderItemService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemResponseDto> getAllByOrderId(Long orderId) {
        return orderItemRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getByIdAndOrderId(Long id, Long orderId) {
        return orderItemMapper.toDto(
            orderItemRepository.findByIdAndOrderId(id, orderId).orElseThrow(
                () -> new EntityNotFoundException("Order item with id '" + id + "' not found")
            )
        );
    }
}
