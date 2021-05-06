package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.CosineSimilarity;
import com.khangse616.serverfashionrs.models.CosineSimilarityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CosineSimilarityRepository extends JpaRepository<CosineSimilarity, CosineSimilarityId> {
    CosineSimilarity findByColumnAndRow(int column, int row);
}
