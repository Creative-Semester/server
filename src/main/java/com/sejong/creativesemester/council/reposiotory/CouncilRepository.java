package com.sejong.creativesemester.council.reposiotory;

import com.sejong.creativesemester.council.entity.Council;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouncilRepository extends JpaRepository<Council,Long>,CouncilRepositoryCustom {

}
