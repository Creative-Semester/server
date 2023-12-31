package com.sejong.creativesemester.major.repository;

import com.sejong.creativesemester.major.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MajorRepository extends JpaRepository<Major, Long> {

    @Override
    Optional<Major> findById(Long majorId);

    Optional<Major> findByName(String majorName);
}