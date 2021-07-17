package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

//    @Query(value = "UPDATE `fashionshop_db`.`order_item` SET `review_status` = :isReview WHERE (`id` = :id);", nativeQuery = true)
//    void updateReviewStatusOrderItem(@Param("id") int id, @Param("isReview") int reviewStatus);

}
