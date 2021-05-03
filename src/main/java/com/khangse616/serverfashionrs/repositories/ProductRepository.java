package com.khangse616.serverfashionrs.repositories;


import com.khangse616.serverfashionrs.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByIdAndTypeId(int id, String typeId);

    @Query("select u from Product u where u.category.id in (:idCategories)")
    List<Product> findProductByCategories(@Param("idCategories") List<Integer> idCategories);
}
