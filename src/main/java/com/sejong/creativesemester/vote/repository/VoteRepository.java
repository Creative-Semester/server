package com.sejong.creativesemester.vote.repository;

import com.sejong.creativesemester.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote,Long> {

}
