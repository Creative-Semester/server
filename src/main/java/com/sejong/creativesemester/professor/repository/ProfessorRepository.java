package com.sejong.creativesemester.professor.repository;

import com.sejong.creativesemester.professor.entity.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfessorRepository extends JpaRepository<ProfessorRepository, Long> {

    @Query("select f from PROFESSOR_TABLE f join f.major as m where m.id=:majorId order by f.name")
    Page<Professor> findAllByOrderByName(@Param(value = "majorId") Long majorId, Pageable pageable);
}
