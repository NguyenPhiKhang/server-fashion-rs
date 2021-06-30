package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.OptionProductVarchar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionProductVarcharRepository extends JpaRepository<OptionProductVarchar, Integer> {
    OptionProductVarchar findById(int id);

    @Query(value = "SELECT * FROM fashionshop_db.option_product_varchar where attribute_id = :attributeId and value is not null and value <> ''", nativeQuery = true)
    List<OptionProductVarchar> findOptionProductVarcharByAttributeId(@Param("attributeId") int attribute_id);
}
