package com.sejong.creativesemester.board.service;

import com.sejong.creativesemester.board.dto.BoardCreateRequestDto;
import com.sejong.creativesemester.board.dto.BoardDetailResponseDto;
import com.sejong.creativesemester.board.dto.BoardModifyRequestDto;
import com.sejong.creativesemester.board.dto.BoardResponseDto;
import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.board.repository.BoardRepository;
import com.sejong.creativesemester.user.repository.UserRepository;
import com.sejong.creativesemester.vote.entity.Vote;
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
        User user = userRepository.findByStudentNum(studentNum).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );
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
    public BoardResponseDto getBoards(Pageable pageable, int page, long majorId){
        Page<Board> boardPage = boardRepository.findAllByOrderByCreatedDateDesc(Long.valueOf(majorId)
                , PageRequest.of(page, TOTAL_ITEMS_PER_PAGE));

        return BoardResponseDto.builder()
                .totalPages(boardPage.getTotalPages())
                .currentPage(boardPage.getNumber())
                .boards(boardPage.getContent().stream().map((board) -> BoardDetailResponseDto.builder()
                        .title(board.getTitle())
                        .content(board.getContent())
                        .image(board.getImage())
                        .build()).collect(Collectors.toList()))
                .build();
    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public BoardDetailResponseDto getDetailBoards(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        return BoardDetailResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .image(board.getImage())
                .build();
    }

    // 게시글 수정
    public void modifyBoard(String studentNum, BoardModifyRequestDto dto) {
        Board board = boardRepository.findById(dto.getBoardId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        User user = userRepository.findById(studentNum).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        board.update(dto.getTitle(), dto.getContent(), dto.getImage());
    }

    public void deleteBoard(String studentNum, Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        User user = userRepository.findById(studentNum).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        boardRepository.delete(board);
    }

}