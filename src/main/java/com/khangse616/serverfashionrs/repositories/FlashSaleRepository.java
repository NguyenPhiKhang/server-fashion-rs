package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.FlashSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlashSaleRepository extends JpaRepository<FlashSale, Integer> {
    @Query(value = "SELECT * FROM fashionshop_db.flashsale where curdate() < date_program or ( curdate()=date_program and curtime()<start_time) or  ( curdate()=date_program and curtime()>start_time and curtime()<end_time) order by date_program asc, start_time asc limit 5", nativeQuery = true)
    List<FlashSale> getFlashSaleTime();
}
