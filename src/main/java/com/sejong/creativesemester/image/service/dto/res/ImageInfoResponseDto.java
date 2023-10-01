package com.sejong.creativesemester.image.service.dto.res;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ImageInfoResponseDto {
    private String imageName;
    private String imageUrl;
}
