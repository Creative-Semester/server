package com.sejong.creativesemester.user.repository;

import com.sejong.creativesemester.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findById(String studentNum);

    Optional<User> findByUsername(String username);

}
