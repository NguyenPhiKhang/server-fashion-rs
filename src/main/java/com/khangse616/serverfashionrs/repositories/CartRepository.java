package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findCartByUserIdOrderByUpdatedAtDesc(int user_id);
    boolean existsCartByUserIdAndProductIdAndProductOptionId(int user_id, int product_id, int productOption_id);
    Cart findCartByUserIdAndProductIdAndProductOptionId(int user_id, int product_id, int productOption_id);
}
