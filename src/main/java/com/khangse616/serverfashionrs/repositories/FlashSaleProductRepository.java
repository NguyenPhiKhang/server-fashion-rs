package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.FlashSaleProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FlashSaleProductRepository extends JpaRepository<FlashSaleProduct, Integer> {
    @Query(value = "SELECT * FROM fashionshop_db.flashsale_product where flashsale_id = :id and sale_amount < quantity order by percent_discount desc, sale_amount desc limit :page, :pageSize", nativeQuery = true)
    List<FlashSaleProduct> getListProductFlashSaleByIdForMobile(@Param("id") int id, @Param("page") int page, @Param("pageSize") int pageSize);

    @Query(value = "SELECT * FROM \n" +
            "fashionshop_db.flashsale_product fp join fashionshop_db.products p \n" +
            "on fp.product_id = p.id \n" +
            "where flashsale_id = :flashsaleId and p.name like %:search% \n" +
            "order by fp.percent_discount desc, fp.sale_amount desc \n" +
            "limit :page, :pageSize", nativeQuery = true)
    List<FlashSaleProduct> getListProductFlashSaleByIdForAdmin(@Param("flashsaleId") int flashsaleId, @Param("search") String search, @Param("page") int page, @Param("pageSize") int pageSize);

    @Query(value = "SELECT count(*) FROM \n" +
            "fashionshop_db.flashsale_product fp join fashionshop_db.products p \n" +
            "on fp.product_id = p.id \n" +
            "where flashsale_id = :flashsaleId and p.name like %:search% \n" +
            "order by fp.percent_discount desc, fp.sale_amount desc \n", nativeQuery = true)
    int countListProductFlashSaleByIdForAdmin(@Param("flashsaleId") int flashsaleId, @Param("search") String search);

    @Query(value = "SELECT fp.* FROM \n" +
            "fashionshop_db.flashsale f join fashionshop_db.flashsale_product fp\n" +
            "on f.id = fp.flashsale_id\n" +
            "where (date_program = curdate()) and (curtime()=start_time or (curtime()>start_time and curtime()<end_time))\n" +
            "and fp.product_id=:productId and fp.sale_amount < quantity", nativeQuery = true)
    FlashSaleProduct findProductFlashSaleInProgress(@Param("productId") int productId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `fashionshop_db`.`flashsale_product` (`flashsale_id`, `product_id`, `percent_discount`, `quantity`) " +
            "VALUES (:flashsaleId, :productId, :discount, :quantity)", nativeQuery = true)
    void insertFlashSale(@Param("flashsaleId") int flashsaleId, @Param("productId") int productId, @Param("discount") int discount, @Param("quantity") int quantity);
}
