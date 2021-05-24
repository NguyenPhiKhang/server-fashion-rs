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

    @Query("select u from Product u where u.category.id in (:idCategories) and u.visibility = true order by u.purpose")
    Page<Product> findProductByCategoriesOrderByPopular(@Param("idCategories") List<Integer> idCategories, Pageable pageable);

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
    List<Product> findProductByListIdProduct(@Param("products")List<Integer> products);

    @Query("select p.shortDescription from Product p where p.visibility = true")
    List<String> getShortDescriptionByVisibilityTrue();
}
