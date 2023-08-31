package com.sejong.creativesemester.board.repository;

import com.sejong.creativesemester.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

<<<<<<< HEAD:src/main/java/com/sejong/creativesemester/board/repository/BoardRepository.java
public interface BoardRepository extends JpaRepository<Board, Long> {
=======
import java.util.List;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
>>>>>>> jg:src/main/java/com/sejong/creativesemester/freeboard/repository/FreeBoardRepository.java

    @Query("select f from FREEBOARD_TABLE as f join f.major as m order by f.createdTime desc")
    Page<Board> findAllByOrderByCreatedDateDesc(long majorId, Pageable pageable);
    ;}