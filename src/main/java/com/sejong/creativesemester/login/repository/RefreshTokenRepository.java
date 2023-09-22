package com.sejong.creativesemester.login.repository;

import com.sejong.creativesemester.login.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {


    Optional<RefreshToken> findByStudentNum(String studentNum);
}
