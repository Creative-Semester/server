package com.sejong.creativesemester.council.repository;

import com.sejong.creativesemester.council.repository.res.CouncilInfoResponseDto;

public interface CouncilRepositoryCustom {
    CouncilInfoResponseDto findCouncilInfoByMajorSort(Long majorSort);
}