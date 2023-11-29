package com.sejong.creativesemester.affair.service;

import com.sejong.creativesemester.affair.controller.RemoveAffairRequest;
import com.sejong.creativesemester.affair.controller.SaveAffairRequest;
import com.sejong.creativesemester.affair.entity.Affair;
import com.sejong.creativesemester.affair.repository.AffairRepository;
import com.sejong.creativesemester.common.format.exception.user.NotHaveRoleException;
import com.sejong.creativesemester.file.entity.File;
import com.sejong.creativesemester.file.repository.FileRepository;
import com.sejong.creativesemester.file.service.FileS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class AffairService {
    private final AffairRepository affairRepository;
    private final FileRepository fileRepository;
    private final FileS3Service fileS3Service;

    public void saveAffair(Authentication authentication, SaveAffairRequest saveAffairRequest) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            throw new NotHaveRoleException();
        }
        File save1 = fileRepository.save(File.builder()
                .fileUrl(saveAffairRequest.getAffairFiles().getAffairUrl())
                .fileName(saveAffairRequest.getAffairFiles().getAffairName())
                .build());
        Affair build = Affair.builder()
                .title(saveAffairRequest.getTitle())
                .usedMoney(saveAffairRequest.getUsedMoney())
                .restMoney(saveAffairRequest.getRestMoney())
                .file(save1)
                .build();
        affairRepository.save(build);
    }

    public List<AffairFileInfoResponse> findAllAffair() {
        List<Affair> all = affairRepository.findAll();
        List<AffairFileInfoResponse> affairFileInfoResponses = new ArrayList<>();
        for (Affair affair : all) {
            affairFileInfoResponses.add(makeAffaireInfoResponse(affair));
        }
        return affairFileInfoResponses;
    }

    public AffairFileInfoResponse findAffair(Long affairId) {
        Affair byId = affairRepository.findById(affairId).orElseThrow(() -> new IllegalStateException("엑셀 파일 없음"));
        AffairFileInfoResponse affairFileInfoResponse = makeAffaireInfoResponse(byId);
        return affairFileInfoResponse;
    }

    public AffairFileInfoResponse makeAffaireInfoResponse(Affair affair) {
        return AffairFileInfoResponse.builder()
                .affairId(affair.getId())
                .usedMoney(affair.getUsedMoney())
                .restMoney(affair.getRestMoney())
                .title(affair.getTitle())
                .fileInfo(FileInfo.builder()
                        .fileName(affair.getFile().getFileName())
                        .fileUrl(affair.getFile().getFileUrl())
                        .build())
                .createdTime(affair.getCreatedTime())
                .build();
    }

    public void removeAffair(Authentication authentication,Long affairId, RemoveAffairRequest removeAffairRequest) {
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            throw new NotHaveRoleException();
        }
        fileS3Service.deleteImageToS3(removeAffairRequest.getFileName());
        affairRepository.deleteById(affairId);
    }
}
