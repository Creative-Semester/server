package com.sejong.creativesemester.professor.repository;

import com.sejong.creativesemester.professor.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
