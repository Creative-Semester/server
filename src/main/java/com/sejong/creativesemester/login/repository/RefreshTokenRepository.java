package com.sejong.creativesemester.login.repository;

import com.sejong.creativesemester.login.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {


    Optional<RefreshToken> findByStudentNum(String studentNum);
}
