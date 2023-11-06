package com.sejong.creativesemester.affair.controller;

import com.sejong.creativesemester.affair.service.AffairFileInfoResponse;
import com.sejong.creativesemester.affair.service.AffairService;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/affair")
public class AffairController {
    private final AffairService affairService;

    @PostMapping("")
    public SuccessResponse saveAffair(@ApiIgnore Authentication authentication, @RequestBody SaveAffairRequest saveAffairRequest) {
        affairService.saveAffair(authentication,saveAffairRequest);
        return SuccessResponse.ok("사무내역이 저장되엇습니다.");
    }

    @GetMapping("")
    public SuccessResponse<List<AffairFileInfoResponse>> findAffairLists() {
        return new SuccessResponse(affairService.findAllAffair());
    }

    @GetMapping("/{affairId}")
    public SuccessResponse<AffairFileInfoResponse> findAffair(@PathVariable Long affairId) {
        return new SuccessResponse(affairService.findAffair(affairId));
    }

    @DeleteMapping("/{affairId}")
    public SuccessResponse removeAffair(@PathVariable Long affairId,
                                        @RequestBody RemoveAffairRequest removeAffairRequest,
                                        @ApiIgnore Authentication authentication) {
        affairService.removeAffair(authentication,affairId,removeAffairRequest);
        return SuccessResponse.ok("사무게시글이 삭제되었습니다.");
    }


}
