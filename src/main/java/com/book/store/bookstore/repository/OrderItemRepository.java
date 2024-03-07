package com.book.store.bookstore.repository;

import com.book.store.bookstore.model.OrderItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("FROM OrderItem oi "
            + "LEFT JOIN FETCH oi.order o "
            + "LEFT JOIN FETCH oi.book "
            + "WHERE o.id = :orderId "
            + "AND oi.isDeleted = FALSE")
    List<OrderItem> findAllByOrderId(Long orderId);

    @Query("FROM OrderItem oi "
            + "LEFT JOIN FETCH oi.order o "
            + "LEFT JOIN FETCH oi.book "
            + "WHERE oi.id = :id "
            + "AND o.id = :orderId "
            + "AND oi.isDeleted = FALSE")
    Optional<OrderItem> findByIdAndOrderId(Long id, Long orderId);
}
