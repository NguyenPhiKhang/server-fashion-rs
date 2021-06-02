package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.ProductUserKey;
import com.khangse616.serverfashionrs.models.SeenProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeenProductRepository extends JpaRepository<SeenProduct, ProductUserKey> {

//    @Query(value = "select count(*) from seen_products where product_id = :productId and user_id=:user_id", nativeQuery = true)
//    int existsSeenProductByProductIdAndUserId();

    boolean existsSeenProductByIdProductIdAndIdUserId(int id_productId, int id_userId);
    SeenProduct findSeenProductByIdProductIdAndIdUserId(int id_productId, int id_userId);

    @Query(value = "SELECT * FROM fashionshop_db.seen_products where user_id = :id_userId and CURRENT_TIMESTAMP - last_time < 7000000 order by last_time desc limit 10", nativeQuery = true)
    List<SeenProduct> findSeenProductByUserAndLastTime(@Param("id_userId") int id_userId);


}
