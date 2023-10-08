package com.sejong.creativesemester.professor.repository;

import com.sejong.creativesemester.professor.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}
