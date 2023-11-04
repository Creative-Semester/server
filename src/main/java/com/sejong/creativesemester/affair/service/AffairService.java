package com.sejong.creativesemester.affair.service;

import com.sejong.creativesemester.affair.controller.SaveAffairRequest;
import com.sejong.creativesemester.affair.entity.Affair;
import com.sejong.creativesemester.affair.repository.AffairRepository;
import com.sejong.creativesemester.file.entity.File;
import com.sejong.creativesemester.file.repository.FileRepository;
import lombok.RequiredArgsConstructor;
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

    public void saveAffair(SaveAffairRequest saveAffairRequest) {
        Affair build = Affair.builder()
                .title(saveAffairRequest.getTitle())
                .usedMoney(saveAffairRequest.getUsedMoney())
                .restMoney(saveAffairRequest.getRestMoney()).build();
        Affair save = affairRepository.save(build);
        File save1 = fileRepository.save(File.builder()
                .fileUrl(saveAffairRequest.getAffairFiles().getAffairUrl())
                .fileName(saveAffairRequest.getAffairFiles().getAffairName())
                .build());
        save.updateFile(save1);
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
    public AffairFileInfoResponse makeAffaireInfoResponse(Affair affair){
        return AffairFileInfoResponse.builder()
                .affairId(affair.getId())
                .usedMoney(affair.getUsedMoney())
                .restMoney(affair.getRestMoney())
                .title(affair.getTitle())
                .fileInfo(FileInfo.builder()
                        .fileName(affair.getFile().getFileName())
                        .fileUrl(affair.getFile().getFileUrl())
                        .build())
                .build();
    }

}
