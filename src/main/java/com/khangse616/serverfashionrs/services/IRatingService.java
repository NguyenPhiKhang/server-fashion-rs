package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.Rating;
import com.khangse616.serverfashionrs.models.dto.CountRatingProductDTO;
import com.khangse616.serverfashionrs.models.dto.InputRatingUpdateDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.AVGRatedProductDTO;
import com.khangse616.serverfashionrs.models.dto.RecommendSystem.RatingRSDTO;

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

    void autoInsertRating();

    List<RatingRSDTO> getUserLeftJoinRating();

    CountRatingProductDTO countStarRatingByUser(int id);

    List<Rating> getRatingByUserAndStar(int userId, int star, int page, int pageSize);

    Rating getRatingByProductAndProductOption(int userId, int productId, int productOptionId);

    void updateReview(int ratingId, InputRatingUpdateDTO inputReview);
}
