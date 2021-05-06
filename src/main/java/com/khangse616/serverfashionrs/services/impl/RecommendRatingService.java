package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.RecommendRating;
import com.khangse616.serverfashionrs.repositories.RecommendRatingRepository;
import com.khangse616.serverfashionrs.services.IRecommendRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendRatingService implements IRecommendRatingService {
    @Autowired
    private RecommendRatingRepository recommendRatingRepository;

    public boolean checkExistUser(int userId){
        return recommendRatingRepository.existsByUserId(userId);
    }

    public RecommendRating save(RecommendRating recommendRating){
        return recommendRatingRepository.save(recommendRating);
    }

    public RecommendRating getById(int user_id){
        return recommendRatingRepository.findByUserId(user_id);
    }

    public void removeAll(){
        recommendRatingRepository.deleteAll();
    }

    @Override
    public RecommendRating findRecommendRatingByUserId(int id) {
        return recommendRatingRepository.findByUserId(id);
    }
}
