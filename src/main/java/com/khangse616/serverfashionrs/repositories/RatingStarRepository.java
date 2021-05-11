package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.RatingStar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingStarRepository extends JpaRepository<RatingStar, Integer> {
    @Query(value = "SELECT r.* FROM products p join rating_star r on p.rating_star_id=r.id where p.id=:idProduct", nativeQuery = true)
    RatingStar getRatingStarByProductId(@Param("idProduct") int idProduct);

    @Query(value = "SELECT r.star1+r.star2+r.star3+r.star4+r.star5 FROM products p join rating_star r on p.rating_star_id=r.id where p.id=:idProduct", nativeQuery = true)
    int totalStarsOfProduct(@Param("idProduct") int idProduct);
}
