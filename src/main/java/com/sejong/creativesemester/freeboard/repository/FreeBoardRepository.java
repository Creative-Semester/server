package com.sejong.creativesemester.freeboard.repository;

import com.sejong.creativesemester.freeboard.entity.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

    Page<FreeBoard> findAllByOrderByCreatedDateDesc(Pageable pageable);
    Page<FreeBoard> findAllByMajorByCreatedDate(long majorId, Pageable pageable);
}
