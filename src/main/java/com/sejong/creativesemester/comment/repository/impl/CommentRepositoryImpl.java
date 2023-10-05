package com.sejong.creativesemester.comment.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sejong.creativesemester.comment.entity.Comment;
import com.sejong.creativesemester.comment.entity.QComment;
import com.sejong.creativesemester.comment.repository.CommentRepositoryCustom;
import com.sejong.creativesemester.comment.repository.dto.CommentListDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.sejong.creativesemester.comment.entity.QComment.comment;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentListDto> findAllCommentOfBoard(Long boardId) {
        return jpaQueryFactory.select(Projections.constructor(
                        CommentListDto.class, comment.id, comment.text, comment.createdTime,comment.user.studentNum))
                .from(comment)
                .where(comment.board.id.eq(boardId))
                .fetch();
    }
}
