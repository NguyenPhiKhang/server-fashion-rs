package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    CartItem findCartItemByCartIdAndProductOptionId(int cart_id, int productOption_id);
    boolean existsCartItemById(int id);
}
