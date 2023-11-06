package com.sejong.creativesemester.council.service;

import com.sejong.creativesemester.common.format.exception.council.NoMatchException;
import com.sejong.creativesemester.common.format.exception.council.NotFoundCodeException;
import com.sejong.creativesemester.common.format.exception.user.NotFoundUserException;
import com.sejong.creativesemester.council.entity.GrantCode;
import com.sejong.creativesemester.council.repository.GrantCodeRepository;
import com.sejong.creativesemester.council.service.res.CouncilInfoResponse;
import com.sejong.creativesemester.council.repository.CouncilRepository;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CouncilService {
    private final CouncilRepository councilRepository;
    private final UserRepository userRepository;
    private final GrantCodeRepository grantCodeRepository;

    public CouncilInfoResponse findCouncilInfo(String studentNum){
        User byStudentNum = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        return councilRepository.findCouncilInfoByMajorId(byStudentNum.getMajor().getId()).toResponse();
    }

    public void grantAuthority(String studentNum, String grantCode){
        User byStudentNum = userRepository.findByStudentNum(studentNum).orElseThrow(NotFoundUserException::new);
        GrantCode byCode = grantCodeRepository.findByGrantCode(grantCode).orElseThrow(NotFoundCodeException::new);

        if(!(byStudentNum.getMajor()).equals(byCode.getMajor())){
            throw new NoMatchException();
        }
        byStudentNum.updateRole();
        log.info("학생 권한: {}", byStudentNum.getRole());
    }
}