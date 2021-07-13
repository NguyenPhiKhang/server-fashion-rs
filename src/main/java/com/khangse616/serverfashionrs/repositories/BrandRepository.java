package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query("select u from Brand u where u.name like %:search%")
    List<Brand> findBrandFilter(@Param("search") String search);
}
