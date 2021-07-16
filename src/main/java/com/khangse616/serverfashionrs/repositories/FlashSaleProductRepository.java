package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.FlashSaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlashSaleProductRepository extends JpaRepository<FlashSaleProduct, Integer> {
    @Query(value = "SELECT * FROM fashionshop_db.flashsale_product where flashsale_id = :id and sale_amount < quantity order by percent_discount desc, sale_amount desc limit :page, :pageSize", nativeQuery = true)
    List<FlashSaleProduct> getListProductFlashSaleByIdForMobile(@Param("id") int id, @Param("page") int page, @Param("pageSize") int pageSize);

    @Query(value = "SELECT fp.* FROM \n" +
            "fashionshop_db.flashsale f join fashionshop_db.flashsale_product fp\n" +
            "on f.id = fp.flashsale_id\n" +
            "where (date_program = curdate()) and (curtime()=start_time or (curtime()>start_time and curtime()<end_time))\n" +
            "and fp.product_id=:productId and fp.sale_amount < quantity", nativeQuery = true)
    FlashSaleProduct findProductFlashSaleInProgress(@Param("productId") int productId);
}
