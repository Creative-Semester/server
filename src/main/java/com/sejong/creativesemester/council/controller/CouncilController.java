package com.sejong.creativesemester.council.controller;


import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.council.service.CouncilService;
import com.sejong.creativesemester.council.service.req.CouncilGrantRequest;
import com.sejong.creativesemester.council.service.res.CouncilInfoResponse;
import com.sejong.creativesemester.user.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/council")
public class CouncilController {
    private final CouncilService councilService;

    @ApiOperation(value = "학생회의 정보를 조회해주는 api")
    @GetMapping("/info")
    public SuccessResponse<CouncilInfoResponse> getInfo(@ApiIgnore Principal principal){
//        log.info("{} : 학생회 정보조회",principal.getName());
        return new SuccessResponse(councilService.findCouncilInfo(principal.getName()));
    }

    @ApiOperation(value = "학과별 인증 코드를 통해 권한을 부여하는 api")
    @PutMapping ("/auth")
    public SuccessResponse getAuth(@ApiIgnore Principal principal,
                                   @RequestBody CouncilGrantRequest councilGrantRequest){
        councilService.grantAuthority(principal.getName(), councilGrantRequest.getGrantCode());
        return SuccessResponse.ok("권한 변경에 성공하였습니다.");
    }
}