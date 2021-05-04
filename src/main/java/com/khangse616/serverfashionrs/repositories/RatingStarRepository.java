package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.RatingStar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingStarRepository extends JpaRepository<RatingStar, Integer> {
}
