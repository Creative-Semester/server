package com.sejong.creativesemester.freeboard.service;

import com.sejong.creativesemester.freeboard.dto.FreeBoardCreateRequestDto;
import com.sejong.creativesemester.freeboard.entity.FreeBoard;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.freeboard.repository.FreeBoardRepository;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FreeBoardService {

    private final FreeBoardRepository freeBoardRepository;
    private final UserRepository userRepository;

    // 게시글 추가
    public void createFreeBoard(Long userId, FreeBoardCreateRequestDto dto){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학생입니다."));

        FreeBoard entity = FreeBoard.builder()
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .image(dto.getImage())
                .major(user.getMajor())
                .build();

        freeBoardRepository.save(entity);

    }

    // 게시글 조회
}
