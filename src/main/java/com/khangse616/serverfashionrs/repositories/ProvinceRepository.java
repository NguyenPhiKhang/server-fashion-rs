package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    @Query(value = "SELECT * FROM provinces order by name", nativeQuery = true)
    List<Province> findALlOrderByName();
}