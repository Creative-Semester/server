package com.sejong.creativesemester.coucil.reposiotory;

import com.sejong.creativesemester.coucil.entity.Council;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouncilRepository extends JpaRepository<Council,Long>,CouncilRepositoryCustom {

}
