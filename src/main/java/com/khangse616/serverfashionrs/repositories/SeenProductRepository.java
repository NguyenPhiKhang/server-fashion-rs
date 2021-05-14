package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.ProductUserKey;
import com.khangse616.serverfashionrs.models.SeenProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SeenProductRepository extends JpaRepository<SeenProduct, ProductUserKey> {

//    @Query(value = "select count(*) from seen_products where product_id = :productId and user_id=:user_id", nativeQuery = true)
//    int existsSeenProductByProductIdAndUserId();

    boolean existsSeenProductByIdProductIdAndIdUserId(int id_productId, int id_userId);
    SeenProduct findSeenProductByIdProductIdAndIdUserId(int id_productId, int id_userId);
}
