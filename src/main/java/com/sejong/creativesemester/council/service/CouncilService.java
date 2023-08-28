package com.sejong.creativesemester.coucil.service;

import com.sejong.creativesemester.coucil.service.res.CouncilInfoResponse;
import com.sejong.creativesemester.coucil.reposiotory.CouncilRepository;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

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
