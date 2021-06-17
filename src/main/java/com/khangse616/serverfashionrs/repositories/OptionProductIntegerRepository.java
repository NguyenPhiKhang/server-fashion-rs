package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.OptionProductInteger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionProductIntegerRepository extends JpaRepository<OptionProductInteger, Integer> {

    @Query(value = "SELECT opi.* FROM fashionshop_db.option_product_int opi join fashionshop_db.catalog_product_int cpi on opi.id = cpi.option_id where cpi.product_id = :productId", nativeQuery = true)
    OptionProductInteger findOptionProductIntegerByProductId(@Param("productId") int productId);
}
