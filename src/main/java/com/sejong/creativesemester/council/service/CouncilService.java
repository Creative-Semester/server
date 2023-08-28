package com.sejong.creativesemester.council.service;

import com.sejong.creativesemester.council.service.res.CouncilInfoResponse;
import com.sejong.creativesemester.council.reposiotory.CouncilRepository;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CouncilService {
    private final CouncilRepository councilRepository;
    private final UserRepository userRepository;
    public CouncilInfoResponse findCouncilInfo(String studentNum){
        User byStudentNum = userRepository.findByStudentNum(studentNum).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );
        return councilRepository.findCouncilInfoByMajorId(byStudentNum.getMajor().getId()).toResponse();
    }
}
