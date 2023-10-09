package com.sejong.creativesemester.image.controller;

import com.sejong.creativesemester.common.format.success.SuccessResponse;
import com.sejong.creativesemester.image.service.ImageService;
import com.sejong.creativesemester.image.service.dto.imageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @ApiOperation(value = "게시판에 이미지 업로드시 사용 api")
    @PostMapping()
    public SuccessResponse<List<imageInfo>> uploadImage(@Parameter(name = "이미지 파일", required = true,
            schema = @Schema(type = "MultipartFile")) @RequestPart List<MultipartFile> files) throws IOException {
        return new SuccessResponse(imageService.uploadImageToS3(files));
    }

    @ApiOperation(value = "게시판에 이미지 업로드하기위해 올렸는데, 취소하고싶을때 사용하는 api")
    @DeleteMapping()
    public SuccessResponse deleteImage(@Parameter(name = "올려진 이미지파일의 imageName값(Url아님)", required = true,
            schema = @Schema(type = "String")) @RequestParam String imageName) {
        imageService.deleteImageToS3(imageName);
        return SuccessResponse.ok("파일이 취소되었습니다.");
    }
}