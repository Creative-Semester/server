package com.sejong.creativesemester.freeboard.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FreeBoardResponseDto {

    private int totalPages;
    private int currentPage;
    private List<FreeBoardDetailResponseDto> freeboards;

}