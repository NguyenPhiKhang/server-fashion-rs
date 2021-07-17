package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Product;
import com.khangse616.serverfashionrs.models.Rating;
import com.khangse616.serverfashionrs.models.User;
import com.khangse616.serverfashionrs.models.dto.CountRatingProductDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.AVGRatedProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query(value = "select count(*) as col_0_0_ from ratings where user_id=:uId", nativeQuery = true)
    int existsByUserId(@Param("uId") int userId);

    boolean existsById(int id);

    boolean existsByUserAndProduct(User user, Product product);

    @Query(value = "select count(distinct user_id) from ratings", nativeQuery = true)
    int countDistinctAllUser();

    @Query(value = "select count(distinct product_id) from ratings", nativeQuery = true)
    int countDistinctAllProduct();

    @Query(value = "select distinct user_id from ratings order by user_id asc", nativeQuery = true)
    List<Integer> findUsersRated();

    @Query(value = "select distinct product_id from ratings order by product_id asc", nativeQuery = true)
    List<Integer> findProductsRated();

    //    @Query(value = "select product_id as ProductId, avg(star) as AvgRated from ratings group by productId order by productId asc;", nativeQuery = true)
    @Query("select new com.khangse616.serverfashionrs.models.dto.RecommendSystem.AVGRatedProductDTO(u.product.id, avg(u.star)) from Rating u group by u.product order by u.product.id asc ")
    List<AVGRatedProductDTO> avgRatedProduct();

    List<Rating> findAllByOrderByProductAsc();

    @Query(value = "SELECT * FROM ratings where product_id=:productId order by time_updated desc limit :p,10;", nativeQuery = true)
    List<Rating> findAllRatingsByProductId(@Param("productId") int productId, @Param("p") int page);

    @Query(value = "SELECT * FROM ratings where product_id=:productId and star=:star order by time_updated desc limit :p,10;", nativeQuery = true)
    List<Rating> findRatingsByProductIdAndStar(@Param("productId") int productId, @Param("star") int star, @Param("p") int page);

    @Query(value = "select r.*\n" +
            "from ratings r \n" +
            "join rating_image ri \n" +
            "on r.id = ri.rating_id \n" +
            "where r.product_id = :productId \n" +
            "group by ri.rating_id \n" +
            "order by r.time_updated desc limit :p,10;", nativeQuery = true)
    List<Rating> findRatingsByProductIdAndHasImage(@Param("productId") int productId, @Param("p") int page);

//    @Query(value = "call count_detail_ratings(:productId);", nativeQuery = true)
//    @Procedure(procedureName = "Rating.countDetailRatings")
//    CountRatingProductDTO countDetailRatings(@Param("productId") int productId);

    @Query(value = "select new com.khangse616.serverfashionrs.models.dto.CountRatingProductDTO(p.ratingStar.star1+p.ratingStar.star2+p.ratingStar.star3+p.ratingStar.star4+p.ratingStar.star5, p.ratingStar.star1, p.ratingStar.star2, p.ratingStar.star3, p.ratingStar.star4, p.ratingStar.star5) \n" +
            "from Product p \n" +
            "where p.id = :productId")
    CountRatingProductDTO countRatingByStar(@Param("productId") int productId);

    @Query(value = "select count(*) from ratings rr \n" +
            "where rr.id in (\n" +
            "select r.id\n" +
            "from ratings r \n" +
            "join rating_image ri \n" +
            "on r.id = ri.rating_id\n" +
            "where r.product_id = :productId\n" +
            "group by ri.rating_id);", nativeQuery = true)
    int countRatingByImage(@Param("productId") int productId);

    @Query(value = "select u.id as userId, r.product_id as productId, r.star from users u left join ratings r on u.id = r.user_id order by u.id" ,nativeQuery = true)
    List<Object[]> getUserLeftJoinRating();

    @Query(value = "SELECT star, count(*) as amount FROM fashionshop_db.ratings where user_id = :userId group by star order by star asc", nativeQuery = true)
    List<Object[]> countStarRatingByUser(@Param("userId") int userId);

    @Query(value = "SELECT * FROM fashionshop_db.ratings where user_id = :userId and star = case when :star=0 then star else :star end order by time_updated desc limit :page, :pageSize", nativeQuery = true)
    List<Rating> findRatingByUserAndStar(@Param("userId") int userId, @Param("star") int star, @Param("page") int page, @Param("pageSize") int pageSize);

    @Query(value = "select * from fashionshop_db.ratings where user_id = :userId and product_id = :productId and product_attribute_id = :productOptionId order by time_updated desc limit 1", nativeQuery = true)
    Rating findRatingByProductAndProductOption(@Param("userId") int userId, @Param("productId") int productId, @Param("productOptionId") int productOptionId);
}
