package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.RecommendRating;

public interface IRecommendRatingService {
    boolean checkExistUser(int userId);

    RecommendRating save(RecommendRating recommendRating);

    RecommendRating getById(int user_id);

    void removeAll();

    RecommendRating findRecommendRatingByUserId(int id);
}
