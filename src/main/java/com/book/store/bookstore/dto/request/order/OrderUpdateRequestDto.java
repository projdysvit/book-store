package com.book.store.bookstore.dto.request.order;

import com.book.store.bookstore.model.Order;
import jakarta.validation.constraints.NotBlank;

public record OrderUpdateRequestDto(
        @NotBlank
        Order.Status status
) {
}
