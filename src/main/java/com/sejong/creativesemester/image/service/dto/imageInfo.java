package com.sejong.creativesemester.image.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class imageInfo {
    private String imageName;
    private String imageUrl;
}
