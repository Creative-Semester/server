package com.sejong.creativesemester.affair.repository;

import com.sejong.creativesemester.affair.entity.Affair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffairRepository extends JpaRepository<Affair,Long> {

}
