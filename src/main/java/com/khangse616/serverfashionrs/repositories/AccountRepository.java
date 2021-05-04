package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsernameAndPassword(String username, String password);
}
