package com.hillel.multi.persistent.repositories;

import com.hillel.multi.persistent.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u")
    List<User> getUsers();

    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> getByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> getById(@Param("id") Integer id);
}
