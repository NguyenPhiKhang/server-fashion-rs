package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update addresses a set a.default_is = 0 where a.user_id = :userId", nativeQuery = true)
    void setDefaultIsFalse(@Param("userId") int userId);
}
