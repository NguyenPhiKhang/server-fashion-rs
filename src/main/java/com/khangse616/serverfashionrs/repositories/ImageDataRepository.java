package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ImageDataRepository extends JpaRepository<ImageData, String> {
    ImageData findImageDataById(String id);
    List<ImageData> findByIdIn(List<String> ids);
}
