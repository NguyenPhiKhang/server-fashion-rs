package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
