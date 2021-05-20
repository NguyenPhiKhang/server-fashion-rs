package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward, Integer> {
}
