package com.sejong.creativesemester.vote.service;

import com.sejong.creativesemester.board.entity.Board;
import com.sejong.creativesemester.board.repository.BoardRepository;
import com.sejong.creativesemester.common.format.exception.board.NotFoundBoardException;
import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.common.format.exception.vote.AlreadyVoteUserException;
import com.sejong.creativesemester.common.format.exception.vote.NotFoundVoteException;
import com.sejong.creativesemester.common.meta.DistributeLock;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import com.sejong.creativesemester.vote.entity.Vote;
import com.sejong.creativesemester.voter.entity.Voter;
import com.sejong.creativesemester.voter.repository.VoterRepository;
import com.sejong.creativesemester.voter.repository.VoterRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class VoteService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final VoterRepository voterRepository;
    private static final String VOTE_KEY = "VOTE_";

    @DistributeLock(identifier = VOTE_KEY,key = "#boardId")
    public void vote(String studentNum,Long boardId, String type) {
        Board boardById = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        User byStudentNum = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        Vote vote = boardById.getVote();
        if(boardById.getVote()==null){
            throw new NotFoundVoteException();
        }
        if(voterRepository.findByVoteIdAndUserId(vote, byStudentNum)){
            throw new AlreadyVoteUserException();
        }
        if(type.equals("agree")){
            vote.increaseAgreeCnt(byStudentNum);
        }else{
            vote.increaseOpposeCnt(byStudentNum);
        }
        voterRepository.save(Voter.builder()
                .user(byStudentNum)
                .vote(vote)
                .build());
    }
}