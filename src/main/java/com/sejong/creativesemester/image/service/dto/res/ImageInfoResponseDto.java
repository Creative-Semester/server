package com.sejong.creativesemester.image.service.dto.res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "이미지 정보값")
@Builder
@Getter
public class ImageInfoResponseDto {
    @Schema(description = "이미지명")
    private String imageName;
    @Schema(description = "이미지 url")
    private String imageUrl;
}
