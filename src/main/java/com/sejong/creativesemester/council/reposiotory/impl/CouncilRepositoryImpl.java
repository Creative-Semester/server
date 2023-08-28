package com.sejong.creativesemester.council.reposiotory.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sejong.creativesemester.council.reposiotory.res.CouncilInfoResponseDto;
import com.sejong.creativesemester.council.reposiotory.CouncilRepositoryCustom;
import lombok.RequiredArgsConstructor;

import static com.sejong.creativesemester.council.entity.QCouncil.council;

@RequiredArgsConstructor
public class CouncilRepositoryImpl implements CouncilRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public CouncilInfoResponseDto findCouncilInfoByMajorId(Long majorId) {
        return jpaQueryFactory.select(
                        Projections.constructor(CouncilInfoResponseDto.class,
                                council.name, council.introduce, council.number)
                )
                .from(council)
                .join(council.major)
                .on(council.major.id.eq(majorId))
                .orderBy(council.createdTime.desc())
                .fetchFirst();
    }
}
