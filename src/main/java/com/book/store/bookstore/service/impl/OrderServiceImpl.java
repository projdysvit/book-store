package com.book.store.bookstore.service.impl;

import com.book.store.bookstore.dto.request.order.OrderRequestDto;
import com.book.store.bookstore.dto.request.order.OrderUpdateRequestDto;
import com.book.store.bookstore.dto.response.order.OrderResponseDto;
import com.book.store.bookstore.exception.EntityNotFoundException;
import com.book.store.bookstore.mapper.OrderMapper;
import com.book.store.bookstore.model.CartItem;
import com.book.store.bookstore.model.Order;
import com.book.store.bookstore.model.OrderItem;
import com.book.store.bookstore.model.ShoppingCart;
import com.book.store.bookstore.model.User;
import com.book.store.bookstore.repository.CartItemRepository;
import com.book.store.bookstore.repository.OrderItemRepository;
import com.book.store.bookstore.repository.OrderRepository;
import com.book.store.bookstore.repository.ShoppingCartRepository;
import com.book.store.bookstore.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderMapper orderMapper;
    
    @Override
    @Transactional
    public OrderResponseDto placeOrder(Long userId, OrderRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId).orElseThrow(
                () -> new EntityNotFoundException("Shopping Cart with id '" + userId 
                                                    + "' not found")
        );
        Order newOrder = orderRepository.save(createOrder(userId, shoppingCart, requestDto));
        Set<OrderItem> orderItems = shoppingCart.getCartItems()
                .stream()
                .map(this::convertToOrderItem)
                .collect(Collectors.toSet());
        orderItems.forEach(orderItem -> orderItem.setOrder(newOrder));

        orderItemRepository.saveAll(orderItems);
        cartItemRepository.deleteAll(shoppingCart.getCartItems());
        
        return orderMapper.toDto(newOrder);
    }

    @Override
    public List<OrderResponseDto> getAllByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId)
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDto updateStatus(Long orderId, OrderUpdateRequestDto requestDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Order with id '" + orderId + "' not found")
        );

        order.setStatus(requestDto.status());

        return orderMapper.toDto(orderRepository.save(order));
    }

    private Order createOrder(Long id, ShoppingCart shoppingCart, OrderRequestDto requestDto) {
        return Order.builder()
                .user(User.builder().id(id).build())
                .status(Order.Status.PENDING)
                .shippingAddress(requestDto.shippingAddress())
                .total(getTotalPrice(shoppingCart.getCartItems()))
                .orderDate(LocalDateTime.now())
                .build();
    }

    private BigDecimal getTotalPrice(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(cartItem -> cartItem.getBook()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private OrderItem convertToOrderItem(CartItem cartItem) {
        return OrderItem.builder()
                .book(cartItem.getBook())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getBook().getPrice())
                .build();
    } 
}
