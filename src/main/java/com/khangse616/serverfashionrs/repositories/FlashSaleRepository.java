package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.FlashSale;
import com.khangse616.serverfashionrs.models.dto.FlashSaleForTableDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FlashSaleRepository extends JpaRepository<FlashSale, Integer> {
    @Query(value = "SELECT * FROM fashionshop_db.flashsale where curdate() < date_program or ( curdate()=date_program and curtime()<start_time) or  ( curdate()=date_program and curtime()>start_time and curtime()<end_time) order by date_program asc, start_time asc limit 5", nativeQuery = true)
    List<FlashSale> getFlashSaleTime();

//    @Query(value="SELECT * FROM fashionshop_db.flashsale order by date_program desc, start_time desc limit :page. :pageSize", nativeQuery = true)


    @Query(value = "SELECT fs.*, count(*) as total_product, sum(fp.sale_amount) as total_sale FROM \n" +
            "fashionshop_db.flashsale fs join \n" +
            "fashionshop_db.flashsale_product fp on fs.id = fp.flashsale_id \n" +
            "group by fs.id\n" +
            "order by date_program desc, start_time desc limit :page, :pageSize", nativeQuery = true)
    List<Object[]> getAllFlashSaleForTable(@Param("page") int page, @Param("pageSize")int pageSize);

    @Query(value = "select count(*) from fashionshop_db.flashsale f where f.id in (SELECT fs.id FROM \n" +
            "fashionshop_db.flashsale fs join \n" +
            "fashionshop_db.flashsale_product fp on fs.id = fp.flashsale_id \n" +
            "group by fs.id\n" +
            "order by date_program desc, start_time desc)", nativeQuery = true)
    int countAllFlashSaleForTable();

    @Query(value = "SELECT fs.*, count(*) as total_product, sum(fp.sale_amount) as total_sale FROM \n" +
            "fashionshop_db.flashsale fs join \n" +
            "fashionshop_db.flashsale_product fp on fs.id = fp.flashsale_id\n" +
            "where curdate() = fs.date_program and (curtime() = fs.start_time or (curtime()>fs.start_time and curtime()<fs.end_time))\n" +
            "group by fs.id\n" +
            "order by date_program desc, start_time desc", nativeQuery = true)
    List<Object[]> getAllFlashSaleForTableInProgress();

    @Query(value = "select count(*) from fashionshop_db.flashsale f where f.id in (SELECT fs.id FROM \n" +
            "fashionshop_db.flashsale fs join \n" +
            "fashionshop_db.flashsale_product fp on fs.id = fp.flashsale_id\n" +
            "where curdate() = fs.date_program and (curtime() = fs.start_time or (curtime()>fs.start_time and curtime()<fs.end_time))\n" +
            "group by fs.id\n" +
            "order by date_program desc, start_time desc);", nativeQuery = true)
    int countAllFlashSaleForTableInProgress();

    @Query(value = "SELECT fs.*, count(*) as total_product, sum(fp.sale_amount) as total_sale FROM \n" +
            "fashionshop_db.flashsale fs join \n" +
            "fashionshop_db.flashsale_product fp on fs.id = fp.flashsale_id\n" +
            "where (curdate() = fs.date_program and (curtime() > fs.start_time)) or fs.date_program < curdate()\n" +
            "group by fs.id\n" +
            "order by date_program desc, start_time desc limit :page, :pageSize", nativeQuery = true)
    List<Object[]> getAllFlashSaleForTableEnd(@Param("page") int page, @Param("pageSize")int pageSize);


    @Query(value = "select count(*) from fashionshop_db.flashsale f where f.id in (SELECT fs.id FROM \n" +
            "fashionshop_db.flashsale fs join \n" +
            "fashionshop_db.flashsale_product fp on fs.id = fp.flashsale_id\n" +
            "where (curdate() = fs.date_program and (curtime() < fs.start_time)) or fs.date_program < curdate()\n" +
            "group by fs.id\n" +
            "order by date_program desc, start_time desc)", nativeQuery = true)
    int countAllFlashSaleForTableEnd();

    @Query(value = "SELECT fs.*, count(*) as total_product, sum(fp.sale_amount) as total_sale FROM \n" +
            "fashionshop_db.flashsale fs join \n" +
            "fashionshop_db.flashsale_product fp on fs.id = fp.flashsale_id\n" +
            "where (curdate() = fs.date_program and (curtime() < fs.start_time)) or fs.date_program > curdate()\n" +
            "group by fs.id\n" +
            "order by date_program desc, start_time desc limit :page, :pageSize", nativeQuery = true)
    List<Object[]> getAllFlashSaleForTableNoOccur(@Param("page") int page, @Param("pageSize")int pageSize);

    @Query(value = "select count(*) from fashionshop_db.flashsale f where f.id in (SELECT fs.id FROM \n" +
            "fashionshop_db.flashsale fs join \n" +
            "fashionshop_db.flashsale_product fp on fs.id = fp.flashsale_id\n" +
            "where (curdate() = fs.date_program and (curtime() < fs.start_time)) or fs.date_program > curdate()\n" +
            "group by fs.id\n" +
            "order by date_program desc, start_time desc)", nativeQuery = true)
    int countAllFlashSaleForTableNoOccur();


}
