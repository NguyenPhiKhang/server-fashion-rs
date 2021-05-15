package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.FavoriteProduct;
import com.khangse616.serverfashionrs.models.ProductUserKey;
import com.khangse616.serverfashionrs.models.SeenProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, ProductUserKey> {
    boolean existsFavoriteProductByIdProductIdAndIdUserId(int id_productId, int id_userId);
    FavoriteProduct findFavoriteProductByIdProductIdAndIdUserId(int id_productId, int id_userId);
}
