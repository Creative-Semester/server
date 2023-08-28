package com.sejong.creativesemester.user.repository;

import com.sejong.creativesemester.user.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

<<<<<<< HEAD

    Optional<User> findById(String studentNum);

    //Optional<User> findByUsername(String username);

=======
    Optional<User> findById(String userId);
    Optional<User> findByStudentNum(String studentNum);
>>>>>>> dev
}
