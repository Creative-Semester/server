package com.sejong.creativesemester.freeboard;

import com.sejong.creativesemester.freeboard.repository.FreeBoardRepository;
import com.sejong.creativesemester.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
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
    FreeBoardRepository freeBoardRepository;

}
