package com.sejong.creativesemester.promise.repository;

import com.sejong.creativesemester.promise.entity.Promise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromiseRepository extends JpaRepository<Promise,Long> {
}
