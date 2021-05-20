package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
}
