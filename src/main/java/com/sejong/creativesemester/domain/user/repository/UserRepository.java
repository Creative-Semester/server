package com.sejong.creativesemester.domain.user.repository;

import com.sejong.creativesemester.domain.user.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long userId);

    Optional<User> findByStudentNum(String studentNum);

    Optional<User> findByNickname(String nickname);
}
