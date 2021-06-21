package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserIdAndStatusId(int user_id, int status_id);

    @Query("select u from Order u order by u.updatedAt desc")
    List<Order> findAllOrderByUpdatedAtDesc();
}
