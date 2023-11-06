package com.sejong.creativesemester.council.repository;

import com.sejong.creativesemester.council.entity.GrantCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GrantCodeRepository extends JpaRepository<GrantCode, Long> {

    Optional<GrantCode> findByGrantCode(String grantCode);
}
