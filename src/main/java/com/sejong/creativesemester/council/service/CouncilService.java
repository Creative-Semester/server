package com.sejong.creativesemester.council.service;

import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.council.service.res.CouncilInfoResponse;
import com.sejong.creativesemester.council.repository.CouncilRepository;
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
        User byStudentNum = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        return councilRepository.findCouncilInfoByMajorId(byStudentNum.getMajor().getId()).toResponse();
    }
}