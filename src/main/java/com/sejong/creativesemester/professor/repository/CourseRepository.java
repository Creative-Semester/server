package com.sejong.creativesemester.professor.repository;

import com.sejong.creativesemester.professor.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from COURSE_TABLE c join c.professor as p where p.id=:professorId order by c.title")
    Page<Course> findAllByOrderByName(@Param(value = "professorId") Long professorId, Pageable pageable);
}
