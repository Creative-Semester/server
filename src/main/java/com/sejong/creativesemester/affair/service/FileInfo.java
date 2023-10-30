package com.sejong.creativesemester.affair.service;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FileInfo {
    private String fileName;
    private String fileUrl;
}
