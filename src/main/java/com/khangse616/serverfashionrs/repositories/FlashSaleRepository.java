package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.FlashSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlashSaleRepository extends JpaRepository<FlashSale, Integer> {
}
