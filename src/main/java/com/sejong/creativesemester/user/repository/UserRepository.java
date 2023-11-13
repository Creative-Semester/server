package com.sejong.creativesemester.user.repository;

import com.sejong.creativesemester.user.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByStudentNum(String studentNum);

    @Modifying
    @Transactional
    @Query("update USER_TABLE SET role='ROLE_COUNCIL' where studentNum=:studentNum")
    void findByStudentNumAndUpdate(@Param(value = "studentNum") String studentNum);
}