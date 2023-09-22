package com.sejong.creativesemester.comment.repository;

import com.sejong.creativesemester.comment.entity.Comment;
import com.sejong.creativesemester.comment.repository.dto.CommentListDto;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentListDto> findAllCommentOfBoard(Long boardId);
}
