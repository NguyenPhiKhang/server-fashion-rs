package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Rating;
import com.khangse616.serverfashionrs.models.dto.CountRatingProductDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.AVGRatedProductDTO;

import java.util.List;

public interface IRatingService {
    int checkUserIsRated(int uId);

    boolean checkExistId(int id);

    int numberUserInRatings();

    int numberProductInRatings();

    List<Rating> getAll();

    List<Integer> getUsersRated();

    List<Integer> getProductsRated();

    List<AVGRatedProductDTO> calcAVGRatedProduct();

    void autoRating();

    Rating save(Rating rating);

    List<Rating> getAllRatingByProductId(int productId, int page);

    List<Rating> getRatingByProductIdAndStar(int productId, int star, int page);

    List<Rating> getRatingByProductIdHasImage(int productId, int page);

    CountRatingProductDTO countRatingByStarOfProduct(int productId);

    int countRatingByImageOfProduct(int productId);
}
