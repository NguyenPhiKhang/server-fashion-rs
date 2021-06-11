package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
