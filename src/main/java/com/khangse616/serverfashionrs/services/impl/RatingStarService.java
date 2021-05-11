package com.khangse616.serverfashionrs.services.impl;

import com.khangse616.serverfashionrs.models.RatingStar;
import com.khangse616.serverfashionrs.repositories.RatingStarRepository;
import com.khangse616.serverfashionrs.services.IRatingStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingStarService implements IRatingStarService {
    @Autowired
    private RatingStarRepository ratingStarRepository;

    @Override
    public RatingStar getRatingStarById(int id) {
        return ratingStarRepository.findById(id).get();
    }

    @Override
    public RatingStar save(RatingStar ratingStar) {
        return ratingStarRepository.save(ratingStar);
    }

    @Override
    public boolean existRatingStarId(int id) {
        return ratingStarRepository.existsById(id);
    }

    @Override
    public RatingStar getRatingStarByProductId(int productId) {
        return ratingStarRepository.getRatingStarByProductId(productId);
    }

    @Override
    public int totalStarsOfProduct(int productId) {
        return ratingStarRepository.totalStarsOfProduct(productId);
    }
}
