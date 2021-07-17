package com.khangse616.serverfashionrs.repositories;


import com.khangse616.serverfashionrs.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByIdAndVisibilityTrue(int id);

    List<Product> findAllByVisibilityTrueOrderByPromotionPercentDesc();

    Product findById(int id);

    @Query("select u from Product u where u.category.id in (:idCategories) and u.visibility = true order by u.updatedAt desc")
    Page<Product> findProductByCategoriesOrderByNew(@Param("idCategories") List<Integer> idCategories, Pageable pageable);

    @Query("select u from Product u where u.category.id in (:idCategories) and u.visibility = true order by u.purpose desc")
    Page<Product> findProductByCategoriesOrderByPopular(@Param("idCategories") List<Integer> idCategories, Pageable pageable);

    Page<Product> findProductByCategoryIdAndVisibilityTrueOrderByPurpose(int category_id, Pageable pageable);

    @Query("select u from Product u where u.category.id in (:idCategories) and u.visibility = true order by u.updatedAt desc")
    Page<Product> findProductByCategoriesOrderByPriceUp(@Param("idCategories") List<Integer> idCategories, Pageable pageable);

    @Query("select u from Product u where u.category.id in (:idCategories) and u.visibility = true order by u.updatedAt desc")
    Page<Product> findProductByCategoriesOrderByPriceDown(@Param("idCategories") List<Integer> idCategories, Pageable pageable);

    // Calculate mean of vote average column
    @Query(value = "SELECT avg(case when (star1+star2+star3+star4+star5)>0 then (star1 + star2*2 + star3*3 + star4*4 + star5*5)/(star1+star2+star3+star4+star5) else 0 end) FROM rating_star;", nativeQuery = true)
    float meanOfVoteAverage();

    @Query(value = "call calculate_quantile();", nativeQuery = true)
    float calculateQuantile();

    @Query(value = "select p.* from rating_star as r join products as p on r.id = p.rating_star_id where (star1+star2+star3+star4+star5)>=:m order by ((((star1+star2+star3+star4+star5)/((star1+star2+star3+star4+star5)+:m))*(case when (star1+star2+star3+star4+star5)>0 then (star1 + star2*2 + star3*3 + star4*4 + star5*5)/(star1+star2+star3+star4+star5) else 0 end)) + ((:m/(:m+(star1+star2+star3+star4+star5)))*:C)) desc limit :page, 10;", nativeQuery = true)
    List<Product> topRatingProducts(@Param("m") float m, @Param("C") float C, @Param("page") int page);

    @Query("select u from Product u where u.id in (:products)")
    List<Product> findProductByListIdProduct(@Param("products") List<Integer> products);

    @Query("select p.shortDescription from Product p where p.visibility = true")
    List<String> getShortDescriptionByVisibilityTrue();

    @Query("select p from Product p where p.visibility = true")
    List<Product> getProductsVisibilityTrue();

    @Query("select p from Product p where p.visibility = true and p.id <> :productId")
    List<Product> getProductAndShortDescriptionExceptProduct(@Param("productId") int productId);

    @Query("select p from Product p where p.visibility = true and p.id not in :listIdProduct")
    List<Product> getProductAndShortDescriptionExceptListProduct(@Param("listIdProduct") List<Integer> listIdProduct);

    @Query(value = "call get_shortdesc_name(:productId)", nativeQuery = true)
    String getShortDescriptionOrName(@Param("productId") int productId);

//    @Query(value = "call get_shortdesc_name_product_seen(:userId)", nativeQuery = true)
//    List<String> getShortDescriptionOrNameByUser(@Param("userId") int userId);


    @Query(value = "Select case when (p.short_description <> '' and p.short_description is not null) then p.short_description else p.name end as txt_description, s.count as count_seen, s.product_id as product_id from `fashionshop_db`.`products` as p inner join `fashionshop_db`.`seen_products` as s on p.id = s.product_id where s.user_id = :userId and CURRENT_TIMESTAMP - s.last_time < 7000000 order by s.last_time desc", nativeQuery = true)
    Page<Object[]> getShortDescriptionOrNameByUser(@Param("userId") int userId, Pageable pageable);

    //    @Query("select p from Product p " +
//            "where p.visibility = true " +
//            "and p.name like %:search% " +
//            "and p.active = CASE WHEN :status = -1 THEN p.active WHEN :status = 1 THEN true ELSE false END " +
//            "and p.category in :listCategories")
    @Query(value = "select * from products p " +
            "where p.visibility = 1 " +
            "and p.name like %:search% " +
            "and p.is_active = CASE WHEN :status = -1 THEN p.is_active WHEN :status = 1 THEN 1 ELSE 0 END " +
            "and p.category_id in (:listCategories) order by updated_at desc limit :page, :pageSize", nativeQuery = true)
    List<Product> findProductFilter(@Param("search") String search, @Param("status") int status, @Param("listCategories") List<Integer> listCategories, @Param("page") int page, @Param("pageSize") int pageSize);

    @Query(value = "select count(*) from products p " +
            "where p.visibility = 1 " +
            "and p.name like %:search% " +
            "and p.is_active = CASE WHEN :status = -1 THEN p.is_active WHEN :status = 1 THEN 1 ELSE 0 END " +
            "and p.category_id in (:listCategories) order by updated_at desc", nativeQuery = true)
    int countProductFilter(@Param("search") String search, @Param("status") int status, @Param("listCategories") List<Integer> listCategories);

    @Query(value = "SELECT distinct p.category_id FROM fashionshop_db.products p where p.category_id is not null;", nativeQuery = true)
    List<Integer> listIdCategoriesOfProduct();

    @Query(value = "SELECT count(*) FROM fashionshop_db.products where brand_id is null and visibility=1", nativeQuery = true)
    int countProductByBrandIsNull();

    @Query(value = "SELECT id FROM fashionshop_db.products where visibility = 1 order by material desc limit 400", nativeQuery = true)
    List<Integer> getListIdProductRandom();
}
