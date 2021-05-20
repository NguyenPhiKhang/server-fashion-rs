package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
//    @Query(value = "SELECT * FROM districts where province_id = :provinceId order by name;", nativeQuery = true)
    List<District> findDistrictByProvinceIdOrderByName(int province_id);
}
