package com.sejong.creativesemester.board.service;

import com.sejong.creativesemester.board.dto.*;
import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.common.format.exception.board.NotFoundBoardException;
import com.sejong.creativesemester.common.format.exception.board.NotMatchBoardAndUserException;
import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.board.repository.BoardRepository;
import com.sejong.creativesemester.user.repository.UserRepository;
import com.sejong.creativesemester.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final int TOTAL_ITEMS_PER_PAGE = 20;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    // 게시글 추가
    public void createBoard(String studentNum, BoardCreateRequestDto dto, BoardType boardType){
        User user = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        Board build = Board.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .image(dto.getImage())
                .major(user.getMajor())
                .boardType(boardType)
                .build();
        boardRepository.save(build);
    }

    // 게시글 조회
    @Transactional(readOnly = true)
    public BoardListResponseDto getBoards(String studentNum, Pageable pageable, int page){
        User byStudentNum = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        Page<Board> boardPage = boardRepository.findAllByOrderByCreatedDateDesc(Long.valueOf(byStudentNum.getMajor().getId())
                , PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));

        return BoardListResponseDto.builder()
                .totalPages(boardPage.getTotalPages())
                .currentPage(boardPage.getNumber())
                .boards(boardPage.getContent().stream().map((board) -> BoardSimpleResponseDto.builder()
                        .boardId(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .build()).collect(Collectors.toList()))
                .build();
    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public BoardDetailResponseDto getDetailBoards(Long boardId,String studentNum){
        Boolean isMine;
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        isMine = isMyBoard(studentNum, board);
        return BoardDetailResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .image(board.getImage())
                .isMine(isMine)
                .build();
    }



    // 게시글 수정
    public void modifyBoard(String studentNum, BoardModifyRequestDto dto,Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        if (!isMyBoard(studentNum, board)) {
            throw new NotMatchBoardAndUserException();
        }
        board.update(dto.getTitle(), dto.getContent(), dto.getImage());
    }

    private static Boolean isMyBoard(String studentNum, Board board) { //게시글의 사용자가 맞는지 확인해주는 메서드
        if(board.getUser().getStudentNum().equals(studentNum)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
    public void deleteBoard(String studentNum, Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        if(isMyBoard(studentNum,board)){
            boardRepository.delete(board);
        }
        throw new NotMatchBoardAndUserException();
    }

}