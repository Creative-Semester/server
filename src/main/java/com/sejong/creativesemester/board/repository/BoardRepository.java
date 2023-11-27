package com.sejong.creativesemester.board.repository;

import com.sejong.creativesemester.board.dto.BoardDetailResponseDto;
import com.sejong.creativesemester.board.entity.Board;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
//    @Query("select f from BOARD_TABLE as f join f.major as m where m.sort=:majorSort and f.boardType='Free' order by f.createdTime desc")
//    Page<Board> findAllByOrderByCreatedDateDesc(@Param(value = "majorId") Long majorSort, Pageable pageable);
}

