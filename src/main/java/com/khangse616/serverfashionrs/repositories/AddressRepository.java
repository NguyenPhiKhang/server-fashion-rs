package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
