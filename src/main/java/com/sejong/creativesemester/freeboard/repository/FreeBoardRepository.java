package com.sejong.creativesemester.freeboard.repository;

import com.sejong.creativesemester.freeboard.entity.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

    @Query("select f from FREEBOARD_TABLE as f join f.major as m order by f.createdTime desc")
    Page<FreeBoard> findAllByOrderByCreatedDateDesc(long majorId, Pageable pageable);
;}
