package com.sejong.creativesemester.board;

import com.sejong.creativesemester.board.repository.BoardRepository;
import com.sejong.creativesemester.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

@EnableJpaAuditing
@Transactional
@SpringBootTest
public class FreeBoardTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

}
