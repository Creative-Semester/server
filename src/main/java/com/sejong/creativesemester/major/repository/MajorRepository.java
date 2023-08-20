package com.sejong.creativesemester.domain.major.repository;

import com.sejong.creativesemester.domain.major.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MajorRepository extends JpaRepository<Major, Long> {

    @Override
    Optional<Major> findById(Long major);
}
