package com.khangse616.serverfashionrs.repositories;

import com.khangse616.serverfashionrs.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "select a.* from `fashionshop_db`.`accounts` a join `fashionshop_db`.`users` u on a.user_id = u.id where (a.username = :username or u.email = :username) and a.password=:password", nativeQuery = true)
    Account findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

//    @Query(value = "INSERT INTO `fashionshop_db`.`accounts` (`username`, `password`, `user_id`, `active`, `permission_id`) VALUES (:username, :password, :user_id, :active, :permission_id)", nativeQuery = true)
//    Account insertAccount(@Param("username") String username, @Param("password") String password, @Param("user_id") int userId, @Param("active")int active, @Param("permission_id") int permissionId);
}
