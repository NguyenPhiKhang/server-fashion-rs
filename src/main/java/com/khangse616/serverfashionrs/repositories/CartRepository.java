package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
