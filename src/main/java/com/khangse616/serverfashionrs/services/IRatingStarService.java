package com.khangse616.serverfashionrs.services;

import com.khangse616.serverfashionrs.models.RatingStar;
import org.springframework.beans.factory.annotation.Autowired;

public interface IRatingStarService {
    RatingStar getRatingStarById(int id);

    RatingStar save(RatingStar ratingStar);

    boolean existRatingStarId(int id);
}
