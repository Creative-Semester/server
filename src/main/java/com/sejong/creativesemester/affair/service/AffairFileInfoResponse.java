package com.sejong.creativesemester.affair.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class AffairFileInfoResponse {
    private Long affairId;
    private String restMoney;
    private String usedMoney;
    private FileInfo fileInfo;
    private String title;

    @JsonFormat(pattern = "YYYY-MM-dd hh:mm:ss")
    private LocalDateTime createdTime;
}
