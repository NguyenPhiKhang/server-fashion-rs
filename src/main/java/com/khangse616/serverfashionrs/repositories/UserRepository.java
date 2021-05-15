package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);
}
