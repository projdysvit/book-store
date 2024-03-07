package com.book.store.bookstore.repository;

import com.book.store.bookstore.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("FROM Order o "
            + "LEFT JOIN FETCH o.orderItems oi "
            + "WHERE o.user.id = :userId "
            + "AND o.isDeleted = FALSE")
    List<Order> findAllByUserId(Long userId);
}
