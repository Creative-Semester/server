package com.sejong.creativesemester.domain.user.repository;

import com.sejong.creativesemester.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String username);

}
