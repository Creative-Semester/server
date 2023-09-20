package com.sejong.creativesemester.voter.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.vote.entity.Vote;
import com.sejong.creativesemester.voter.repository.VoterRepositoryCustom;
import lombok.RequiredArgsConstructor;

import static com.sejong.creativesemester.voter.entity.QVoter.voter;

@RequiredArgsConstructor
public class VoterRepositoryImpl implements VoterRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public boolean findByVoteIdAndUserId(Vote vote, User user) {
        return jpaQueryFactory
                .selectOne()
                .from(voter)
                .where(voter.user.eq(user))
                .where(voter.vote.eq(vote))
                .fetchFirst()!= null;
        //해당하는 투표목록이 존재하면 true, 없으면 false
    }
}
