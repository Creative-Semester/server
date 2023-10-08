package com.sejong.creativesemester.comment.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sejong.creativesemester.comment.repository.CommentRepositoryCustom;
import com.sejong.creativesemester.comment.repository.dto.CommentListDto;
import com.sejong.creativesemester.user.controller.UserCommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.sejong.creativesemester.comment.entity.QComment.comment;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<CommentListDto> findAllCommentOfBoard(Long boardId, Pageable pageable) {
        List<CommentListDto> commentListDtos = jpaQueryFactory.select(Projections.constructor(
                        CommentListDto.class, comment.id, comment.text, comment.createdTime, comment.user.studentNum))
                .from(comment)
                .where(comment.board.id.eq(boardId))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(comment.createdTime.asc())
                .fetch();
        int size = jpaQueryFactory
                .select(comment.count())
                .from(comment)
                .where(comment.board.id.eq(boardId)).fetch().size();
        return new PageImpl<>(commentListDtos, pageable, size);
    }

    @Override
    public Page<UserCommentResponseDto> findAllByStudentNumDesc(String studentNum, Pageable pageable) {
        List<UserCommentResponseDto> commentReponseList = jpaQueryFactory.select(Projections.constructor(
                        UserCommentResponseDto.class
                        , comment.id.as("commentId")
                        , comment.text.as("text")
                        , comment.createdTime.as("createdTime")
                        , comment.board.id.as("boardId"))
                )
                .from(comment)
                .where(comment.user.studentNum.eq(studentNum))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(comment.createdTime.desc())
                .fetch();

        int size = jpaQueryFactory.select(comment.count())
                .from(comment)
                .where(comment.user.studentNum.eq(studentNum)).fetch().size();
        return new PageImpl<>(commentReponseList, pageable, size);

    }
}
