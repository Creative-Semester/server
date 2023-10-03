package com.sejong.creativesemester.department.service;

import com.sejong.creativesemester.common.config.format.converter.RoundToSecondDigit;
import com.sejong.creativesemester.council.entity.Council;
import com.sejong.creativesemester.council.repository.CouncilRepository;
import com.sejong.creativesemester.department.repository.dto.PromisesForDepartmentDto;
import com.sejong.creativesemester.department.repository.impl.DepartmentRepositoryImpl;
import com.sejong.creativesemester.department.service.res.DepartmentInfoResponseDto;
import com.sejong.creativesemester.department.service.res.PromiseContentsResponseDto;
import com.sejong.creativesemester.department.service.res.PromisePercentageResponseDto;
import com.sejong.creativesemester.user.entity.User;
import com.sejong.creativesemester.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DepartmentService {

    private final DepartmentRepositoryImpl departmentRepositoryImpl;
    private final UserRepository userRepository;
    private final RoundToSecondDigit roundToSecondDigit;
    private final CouncilRepository councilRepository;
    static Double totalPromise = 0D;
    static Double totalImplementation = 0D;
    public PromisePercentageResponseDto getPromisePercentage(String studentNum) {
        HashMap<String, Double> map = new HashMap<>();
        User byStudentNum = userRepository.findByStudentNum(studentNum).orElseThrow(
                () -> new IllegalArgumentException("없는사용자")
        );
        List<Council> byMajorIdOrderByCreatedTimeDesc = councilRepository.findByMajor_IdOrderByCreatedTimeDesc(byStudentNum.getMajor().getId());
        if(byMajorIdOrderByCreatedTimeDesc.isEmpty()){
            throw new IllegalArgumentException("해당 학생회가 없습니다");
        }
        List<PromisesForDepartmentDto> promisesForDepartment = departmentRepositoryImpl.getCountOfPromises(byMajorIdOrderByCreatedTimeDesc.get(0).getId());
        /*
         - 각 부서별 공약이행도 퍼센트로 변환
         - 전체공약수 및 이행수 계산
         */
        promisesForDepartment.forEach(promisesForDepartmentDto -> {
            totalPromise += promisesForDepartmentDto.getPromiseCount();
                    totalImplementation += promisesForDepartmentDto.getImplementationCount();
            map.put(promisesForDepartmentDto.getDepartmentName(),
                    roundToSecondDigit.convert(promisesForDepartmentDto.getImplementationCount() / promisesForDepartmentDto.getPromiseCount())
            );
        });
        //전체 공약 및 이행 퍼센트계산
        map.put("total", roundToSecondDigit.convert(totalImplementation / totalPromise));

        //responseDto로 변환
        PromisePercentageResponseDto responseDto = PromisePercentageResponseDto.builder().promisePercentage(map).build();

        return responseDto;
    }

    public List<PromiseContentsResponseDto> getPromises(String studentNum, Long departmentId) {
        User byStudentNum = userRepository.findByStudentNum(studentNum).orElseThrow(()->new NullPointerException("사용자 없음"));
        List<Council> byMajorIdOrderByCreatedTimeDesc = councilRepository.findByMajor_IdOrderByCreatedTimeDesc(byStudentNum.getMajor().getId());
        return departmentRepositoryImpl.getPromises(byMajorIdOrderByCreatedTimeDesc.get(0).getId(), departmentId);

    }

    public List<DepartmentInfoResponseDto> getDepartmentLists(String studentNum) {
        User byStudentNum = userRepository.findByStudentNum(studentNum).orElseThrow(()->new NullPointerException("사용자 없음"));
        List<Council> byMajorIdOrderByCreatedTimeDesc = councilRepository.findByMajor_IdOrderByCreatedTimeDesc(byStudentNum.getMajor().getId());
        return departmentRepositoryImpl.getDepartmentsInfo(byMajorIdOrderByCreatedTimeDesc.get(0).getId());
    }
}
