package com.sejong.creativesemester.domain.freeboard.repository;

import com.sejong.creativesemester.domain.freeboard.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
}
