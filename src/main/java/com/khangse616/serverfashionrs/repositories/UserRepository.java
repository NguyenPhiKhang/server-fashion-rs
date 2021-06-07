package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);

    @Query(value = "select u.id from User u")
    List<Integer> getAllIdUser();
}
