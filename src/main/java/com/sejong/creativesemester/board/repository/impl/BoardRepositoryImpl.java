package com.sejong.creativesemester.board.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.board.repository.BoardRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.sejong.creativesemester.board.entity.QBoard.board;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Board> findAllByBoardTypeAndMajor(Long majorId, BoardType boardType, Pageable pageable) {
        List<Board> fetch = queryFactory.selectFrom(board)
                .where(board.boardType.eq(boardType)
                        , board.major.id.eq(majorId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        int size = queryFactory.select(board.count())
                .from(board)
                .where(board.boardType.eq(boardType)
                        , board.major.id.eq(majorId)).fetch().size();
        return new PageImpl<>(fetch, pageable, size);
    }
}
