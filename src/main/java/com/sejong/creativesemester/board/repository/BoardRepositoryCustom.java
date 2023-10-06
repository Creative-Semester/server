package com.sejong.creativesemester.board.repository;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.board.entity.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<Board> findAllByBoardTypeAndMajor(Long majorId, BoardType boardType, Pageable pageable);
}