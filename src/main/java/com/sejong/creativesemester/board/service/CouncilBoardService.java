package com.sejong.creativesemester.board.service;

import com.sejong.creativesemester.board.controller.req.BoardModifyRequest;
import com.sejong.creativesemester.board.dto.BoardCreateRequestDto;
import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.board.repository.BoardRepository;
import com.sejong.creativesemester.common.format.exception.board.NotFoundBoardException;
import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import com.sejong.creativesemester.vote.entity.Vote;
import com.sejong.creativesemester.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class CouncilBoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final VoteRepository voteRepository;

    public void createCouncilBoard(String studentNum, BoardCreateRequestDto dto, BoardType boardType)throws Exception{
        Vote vote=null;
        User user = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        Board build = Board.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .image(dto.getImage())
                .major(user.getMajor())
                .boardType(boardType)
                .build();
        Board saveBoard = boardRepository.save(build);
        if(boardType.getType().equals("Vote")) {
            vote = Vote.initVote(saveBoard);
            Vote saveVote = voteRepository.save(vote);
            saveBoard.makeVote(saveVote);
            System.out.println(saveVote.getBoard().getId());
        }
    }
}
