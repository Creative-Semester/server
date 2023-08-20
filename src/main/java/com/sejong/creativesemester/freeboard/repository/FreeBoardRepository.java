package com.sejong.creativesemester.freeboard.repository;

import com.sejong.creativesemester.freeboard.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
}
