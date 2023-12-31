package com.sejong.creativesemester.comment.repository;

import com.sejong.creativesemester.comment.repository.dto.CommentListDto;
import com.sejong.creativesemester.user.controller.UserCommentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryCustom {
    Page<CommentListDto> findAllCommentOfBoard(Long boardId, Pageable pageable);

    Page<UserCommentResponseDto> findAllByStudentNumDesc(String studentNum, Pageable pageable);
}
