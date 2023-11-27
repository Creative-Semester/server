package com.sejong.creativesemester.board.repository;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.board.entity.BoardType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<Board> findAllByBoardTypeAndMajorDesc(Long majorSort, BoardType boardType, Pageable pageable);

    Page<Board> findAllByStudentNumDesc(String studentNum, Pageable pageable);
}
