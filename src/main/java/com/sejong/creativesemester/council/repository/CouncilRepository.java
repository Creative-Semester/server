package com.sejong.creativesemester.council.repository;

import com.sejong.creativesemester.council.entity.Council;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface CouncilRepository extends JpaRepository<Council,Long>,CouncilRepositoryCustom {
    @Query("select c from COUNCIL_TABLE c where c.major.sort=:majorSort order by c.createdTime desc")
    List<Council> findBySortOrderByCreatedTimeDesc(@Param(value = "majorSort") Long majorSort);
}