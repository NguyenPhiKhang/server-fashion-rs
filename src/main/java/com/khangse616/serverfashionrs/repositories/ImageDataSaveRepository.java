package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.ImageDataSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDataSaveRepository extends JpaRepository<ImageDataSave, String> {
}
