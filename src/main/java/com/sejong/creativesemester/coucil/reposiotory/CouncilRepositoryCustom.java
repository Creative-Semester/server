package com.sejong.creativesemester.coucil.reposiotory;

import com.sejong.creativesemester.coucil.reposiotory.res.CouncilInfoResponseDto;

public interface CouncilRepositoryCustom {
    CouncilInfoResponseDto findCouncilInfoByMajorId(Long majorId);
}
