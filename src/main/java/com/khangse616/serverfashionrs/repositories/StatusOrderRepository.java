package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusOrderRepository extends JpaRepository<StatusOrder, Integer> {
}
