package com.sejong.creativesemester.department.repository;

import com.sejong.creativesemester.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long>,DepartmentRepositoryCustom {
}
