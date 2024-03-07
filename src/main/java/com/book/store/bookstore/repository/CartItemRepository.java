package com.book.store.bookstore.repository;

import com.book.store.bookstore.model.CartItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("FROM CartItem ci "
            + "WHERE ci.shoppingCart.id = :shoppingCartId "
            + "AND ci.book.id = :bookId "
            + "AND ci.isDeleted = FALSE")
    Optional<CartItem> findByShoppingCartIdAndBookId(Long shoppingCartId, Long bookId);
}
