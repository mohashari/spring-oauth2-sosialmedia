package com.kodascript.oauth2sosial.repository;

import com.kodascript.oauth2sosial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
