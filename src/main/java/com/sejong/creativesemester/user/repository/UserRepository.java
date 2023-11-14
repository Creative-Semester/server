package com.sejong.creativesemester.user.repository;

import com.sejong.creativesemester.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByStudentNum(String studentNum);

    @Modifying
    @Transactional
    @Query("update USER_TABLE SET role='ROLE_COUNCIL' where studentNum=:studentNum")
    void findByStudentNumAndUpdate(@Param(value = "studentNum") String studentNum);

    @Query("select U from USER_TABLE as U JOIN BOARD_TABLE as B on U=B.user where B.id=:boardId")
    User findByBoardId(@Param(value = "boardId") Long boardId);
}