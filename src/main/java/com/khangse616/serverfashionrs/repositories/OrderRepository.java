package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserIdAndStatusId(int user_id, int status_id);

    @Query("select u from Order u order by u.updatedAt desc")
    List<Order> findAllOrderByUpdatedAtDesc();

    @Query(value = "select o.* from orders o join users u on o.user_id = u.id where u.name like %:search% and o.user_id = case when :userId = 0 then o.user_id else :userId end and o.status = case when :statusId = 0 then o.status else :statusId end order by o.status asc, o.created_at desc limit :page, :pageSize", nativeQuery = true)
    List<Order> findOrdersFilterForAdmin(@Param("userId") int userId, @Param("statusId") int statusId, @Param("search") String search, @Param("page") int page, @Param("pageSize") int pageSize);

    @Query(value = "select count(o.*) from orders o join users u on o.user_id = u.id where u.name like %:search% and o.user_id = case when :userId = 0 then o.user_id else :userId end and o.status = case when :statusId = 0 then o.status else :statusId end ", nativeQuery = true)
    int countOrdersFilterForAdmin(@Param("userId") int userId, @Param("statusId") int statusId, @Param("search") String search);
}
