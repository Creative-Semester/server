package com.sejong.creativesemester.professor.repository;

import com.sejong.creativesemester.professor.entity.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("select f from PROFESSOR_TABLE f join f.major as m where m.sort=:majorSort order by f.name")
    Page<Professor> findAllByOrderByName(@Param(value = "majorSort") Long majorSort, Pageable pageable);

    Optional<Professor> findById(Long aLong);
}
