package com.sejong.creativesemester.image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.sejong.creativesemester.board.entity.BoardType;
import com.sejong.creativesemester.image.repository.ImageRepository;
import com.sejong.creativesemester.image.service.dto.imageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public List<imageInfo> uploadImageToS3(List<MultipartFile> files) throws IOException {
        ArrayList<imageInfo> imageUrlList = new ArrayList<>();
        for (MultipartFile file : files) {
            String s3FileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            InputStream inputStream = file.getInputStream();
            objectMetadata.setContentLength(inputStream.available());
            amazonS3Client.putObject(bucket, s3FileName, file.getInputStream(), objectMetadata);
            String savedImgUrl = amazonS3Client.getUrl(bucket, s3FileName).toString();
            imageUrlList.add(imageInfo.builder()
                    .imageName(s3FileName)
                    .imageUrl(savedImgUrl)
                    .build());
            inputStream.close();
        }
        return imageUrlList;
    }

    public void deleteImageToS3(String imageName) {
        amazonS3Client.deleteObject(bucket,imageName);
    }
}