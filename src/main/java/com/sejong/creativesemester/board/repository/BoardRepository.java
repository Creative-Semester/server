package com.sejong.creativesemester.board.repository;

import com.sejong.creativesemester.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select f from BOARD_TABLE as f join f.major as m order by f.createdTime desc")
    Page<Board> findAllByOrderByCreatedDateDesc(long majorId, Pageable pageable);
    ;}