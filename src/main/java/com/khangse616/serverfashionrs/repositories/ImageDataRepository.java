package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, Integer> {
    ImageData findById(String id);
}
