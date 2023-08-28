package com.sejong.creativesemester.council.reposiotory.res;

import com.sejong.creativesemester.council.service.res.CouncilInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CouncilInfoResponseDto {
    private String name;
    private String introduce;
    private Integer number;
    public CouncilInfoResponse toResponse(){
        return CouncilInfoResponse.builder()
                .name(name)
                .introduce(introduce)
                .number(number)
                .build();
    }

}
