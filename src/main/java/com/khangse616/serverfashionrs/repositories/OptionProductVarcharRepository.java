package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.OptionProductVarchar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionProductVarcharRepository extends JpaRepository<OptionProductVarchar, Integer> {
    OptionProductVarchar findById(int id);
}
