package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Favorite;
import com.khangse616.serverfashionrs.models.ProductUserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, ProductUserKey> {
    boolean existsFavoriteProductByIdProductIdAndIdUserId(int id_productId, int id_userId);
    Favorite findFavoriteProductByIdProductIdAndIdUserId(int id_productId, int id_userId);
    List<Favorite> getFavoriteByIdUserIdAndLikedTrueOrderByTimeUpdatedDesc(int id_userId);
    boolean existsFavoriteByIdProductIdAndIdUserIdAndLikedTrue(int id_productId, int id_userId);
}
