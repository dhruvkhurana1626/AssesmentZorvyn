package com.example.AssesmentZorvyn.dao;

import com.example.AssesmentZorvyn.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String mail);

    List<User> findAllByActive();
}
