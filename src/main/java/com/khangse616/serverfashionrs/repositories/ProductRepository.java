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

    @Query("select u from Product u where u.category.id in (:idCategories) and u.visibility = true order by u.updatedAt desc")
    Page<Product> findProductByCategoriesOrderByNew(@Param("idCategories") List<Integer> idCategories, Pageable pageable);

    @Query("select u from Product u where u.category.id in (:idCategories) and u.visibility = true order by u.purpose")
    Page<Product> findProductByCategoriesOrderByPopular(@Param("idCategories") List<Integer> idCategories, Pageable pageable);

    @Query("select u from Product u where u.category.id in (:idCategories) and u.visibility = true order by u.updatedAt desc")
    Page<Product> findProductByCategoriesOrderByPriceUp(@Param("idCategories") List<Integer> idCategories, Pageable pageable);

    @Query("select u from Product u where u.category.id in (:idCategories) and u.visibility = true order by u.updatedAt desc")
    Page<Product> findProductByCategoriesOrderByPriceDown(@Param("idCategories") List<Integer> idCategories, Pageable pageable);
}
