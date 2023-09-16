package com.sejong.creativesemester.voter.repository;

import com.sejong.creativesemester.voter.entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter,Long>,VoterRepositoryCustom {
}
