package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(int id);

    @Query(value = "select u.id from User u")
    List<Integer> getAllIdUser();

    @Query(value = "select * from users u where u.name like %:search% and u.active = case when :active = -1 then u.active else :active end order by u.time_created desc limit :page, :pageSize", nativeQuery = true)
    List<User> getListUserFilter(@Param("search") String search, @Param("active") int active, @Param("page") int page, @Param("pageSize") int pageSize);

    @Query(value = "select count(*) from users u where u.name like %:search% and u.active = case when :active = -1 then u.active else :active end", nativeQuery = true)
    int countListUserFilter(@Param("search") String search, @Param("active") int active);
}
