package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {
    List<Ward> findWardByDistrictIdOrderByName(int district_id);
}
