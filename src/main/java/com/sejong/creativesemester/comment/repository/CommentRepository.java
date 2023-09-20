package com.sejong.creativesemester.comment.repository;

import com.sejong.creativesemester.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
