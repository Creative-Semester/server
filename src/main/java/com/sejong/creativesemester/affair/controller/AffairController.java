package com.sejong.creativesemester.affair.controller;

import com.sejong.creativesemester.affair.service.AffairFileInfoResponse;
import com.sejong.creativesemester.affair.service.AffairService;
import com.sejong.creativesemester.common.format.exception.user.NotHaveRoleException;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static com.sejong.creativesemester.user.entity.Role.ROLE_ADMIN;
import static com.sejong.creativesemester.user.entity.Role.ROLE_COUNCIL;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/affair")
public class AffairController {
    private final AffairService affairService;

    @ApiOperation(value = "사무내역을 작성하고 생성하는 api",
            notes = "학생회의 사무부서를 맡고 있는 부장이 사무내역을 작성합니다.")
    @PostMapping("/council")
    public SuccessResponse<String> saveAffair(@ApiIgnore Authentication authentication, @RequestBody SaveAffairRequest saveAffairRequest) {
        affairService.saveAffair(authentication, saveAffairRequest);
        return SuccessResponse.ok("사무내역이 저장되엇습니다.");
    }

    @ApiOperation(value = "사무내역 리스트를 조회하는 api",
            notes = "저장되었던 모든 사무내역을 리스트로 조회합니다.")
    @GetMapping("")
    public SuccessResponse<List<AffairFileInfoResponse>> findAffairLists() {
        return new SuccessResponse(affairService.findAllAffair());
    }

    @ApiOperation(value = "특정 사무내역을 조회하는 api",
            notes = "affairId를 통해 원하는 사무내역을 상세조회합니다.")
    @GetMapping("/{affairId}")
    public SuccessResponse<AffairFileInfoResponse> findAffair(@PathVariable Long affairId) {
        return new SuccessResponse(affairService.findAffair(affairId));
    }

    @ApiOperation(value = "특정 사무내역을 삭제하는 api",
            notes = "affairId를 통해 원하는 사무내역을 학생회 측에서 삭제합니다.")
    @DeleteMapping("/council/{affairId}")
    public SuccessResponse<String> removeAffair(@PathVariable Long affairId,
                                                @RequestBody RemoveAffairRequest removeAffairRequest,
                                                @ApiIgnore Authentication authentication) {
        if (!(authentication.getAuthorities().contains("ROLE_COUNCIL") && authentication.getAuthorities().contains("ROLE_ADMIN"))) {
            throw new NotHaveRoleException();
        }
        affairService.removeAffair(authentication, affairId, removeAffairRequest);
        return SuccessResponse.ok("사무게시글이 삭제되었습니다.");
    }
}
