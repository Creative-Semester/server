package com.sejong.creativesemester.voter.repository;

import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.vote.entity.Vote;

public interface VoterRepositoryCustom {
    boolean findByVoteIdAndUserId(Vote vote, User user);
}
