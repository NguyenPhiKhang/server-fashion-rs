package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    CartItem findCartItemByCartIdAndProductOptionId(int cart_id, int productOption_id);

    CartItem findCartItemByCartIdAndProductIdAndProductOptionIsNull(int cart_id, int product_id);

    boolean existsCartItemById(int id);

    @Query(value = "SELECT ci.* FROM cart_item ci join carts c on ci.cart_id = c.id join users u on u.id = c.user_id where u.id =:user_id order by ci.updated_at desc", nativeQuery = true)
    List<CartItem> findCartItemByUserId(@Param("user_id") int userId);

    @Query(value = "SELECT ci.* FROM cart_item ci join carts c on ci.cart_id = c.id join users u on u.id = c.user_id where u.id=:userId and ci.product_option_id=:productOId and ci.id <> :cartItemId", nativeQuery = true)
    CartItem findCartItemOtherToMerge(@Param("userId") int userId, @Param("productOId") int productOId, @Param("cartItemId") int cartItemId);
}
