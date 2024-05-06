package com.ra.repository;

import com.ra.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {
//    Optional<User> findByUserName(String userName);
//    User findByUserName (String userName);
    @Query("select u from User u where u.email = :email")
    User findByEmail(String email);


}
