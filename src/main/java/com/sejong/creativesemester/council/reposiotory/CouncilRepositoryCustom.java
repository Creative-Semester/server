package com.sejong.creativesemester.council.reposiotory;

import com.sejong.creativesemester.council.reposiotory.res.CouncilInfoResponseDto;

public interface CouncilRepositoryCustom {
    CouncilInfoResponseDto findCouncilInfoByMajorId(Long majorId);
}
