package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.ProductUserKey;
import com.khangse616.serverfashionrs.models.SeenProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeenProductRepository extends JpaRepository<SeenProduct, ProductUserKey> {

}
