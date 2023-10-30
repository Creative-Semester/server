package com.sejong.creativesemester.affair.controller;

import com.sejong.creativesemester.affair.service.AffairService;
import com.sejong.creativesemester.common.format.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/affair")
public class AffairController {
    private final AffairService affairService;

    @PostMapping("")
    public SuccessResponse saveAffair(@RequestBody SaveAffairRequest saveAffairRequest){
        affairService.saveAffair(saveAffairRequest);
        return SuccessResponse.ok("사무내역이 저장되엇습니다.");
    }

    @GetMapping("")
    public SuccessResponse findAffairLists(){
        return new SuccessResponse(affairService.findAllAffair());
    }

    @GetMapping("/{affairId}")
    public SuccessResponse findAffair(@PathVariable Long affairId){
        return new SuccessResponse(affairService.findAffair(affairId));
    }
}
