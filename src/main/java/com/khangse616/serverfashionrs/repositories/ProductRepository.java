package com.khangse616.serverfashionrs.repositories;


import com.khangse616.serverfashionrs.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByIdAndTypeId(int id, String typeId);
}
