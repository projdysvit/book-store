package com.book.store.bookstore.controller;

import com.book.store.bookstore.dto.request.order.OrderRequestDto;
import com.book.store.bookstore.dto.request.order.OrderUpdateRequestDto;
import com.book.store.bookstore.dto.response.order.OrderResponseDto;
import com.book.store.bookstore.dto.response.orderitem.OrderItemResponseDto;
import com.book.store.bookstore.model.User;
import com.book.store.bookstore.service.OrderItemService;
import com.book.store.bookstore.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
@Tag(name = "Order management", description = "Endpoints for managing orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Place order", description = "Place order")
    public OrderResponseDto placeOrder(
            Authentication authentication,
            @RequestBody OrderRequestDto requestDto
    ) {
        return orderService.placeOrder(
                ((User) authentication.getPrincipal()).getId(), 
                requestDto
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get all orders", description = "Return all orders")
    public List<OrderResponseDto> getAll(Authentication authentication) {
        return orderService.getAllByUserId(((User) authentication.getPrincipal()).getId());
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Change order status")
    public OrderResponseDto updateStatus(
            @PathVariable Long id,
            @RequestBody OrderUpdateRequestDto requestDto
    ) {
        return orderService.updateStatus(id, requestDto);
    }

    @GetMapping("/{id}/items")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get all items by order id")
    public List<OrderItemResponseDto> getAllById(@PathVariable Long id) {
        return orderItemService.getAllByOrderId(id);
    }

    @GetMapping("/{id}/items/{itemId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Get item from order by id")
    public OrderItemResponseDto getItemById(@PathVariable Long id, @PathVariable Long itemId) {
        return orderItemService.getByIdAndOrderId(itemId, id);
    }
}
