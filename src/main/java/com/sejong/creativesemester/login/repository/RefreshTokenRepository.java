package com.sejong.creativesemester.login.repository;

import com.sejong.creativesemester.login.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {


    void deleteById(String id);

    Optional<RefreshToken> findById(String id);
}
