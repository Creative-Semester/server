package com.sejong.creativesemester.professor.repository;


import com.sejong.creativesemester.professor.entity.Evaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query("select e from EVALUATION_TABLE e join e.course as c where c.id=:courseId order by e.createdTime")
    Page<Evaluation> findAllByCreatedTime(@Param(value = "courseId") Long courseId, Pageable pageable);

}
