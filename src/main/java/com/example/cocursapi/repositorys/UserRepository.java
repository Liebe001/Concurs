package com.example.cocursapi.repositorys;

import com.example.cocursapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByUsername(String username);

    Boolean existsUsersByUsername(String Username);

    Boolean existsUsersByEmail(String email);

}