package com.sejong.creativesemester.department.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sejong.creativesemester.department.repository.DepartmentRepositoryCustom;
import com.sejong.creativesemester.department.repository.dto.PromisesForDepartmentDto;
import com.sejong.creativesemester.department.service.res.DepartmentInfoResponseDto;
import com.sejong.creativesemester.department.service.res.PromiseContentsResponseDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.sejong.creativesemester.department.entity.QDepartment.department;
import static com.sejong.creativesemester.promise.entity.QPromise.promise;

@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PromisesForDepartmentDto> getCountOfPromises(Long councilId) {
        List<PromisesForDepartmentDto> list = jpaQueryFactory.select(Projections.constructor(
                        PromisesForDepartmentDto.class,
                        department.name,
                        department.promises.size().doubleValue(),//소수자리 표현은위해 double형 사용
                        //이행상태가 true라면 1을 반환한후 값을 더함
                        new CaseBuilder()
                                .when(promise.implementation.isTrue())
                                .then(1L).otherwise(0L).sum()
                                .doubleValue()
                ))
                .from(department)
                .leftJoin(department.promises, promise)
                .where(department.council.id.eq(councilId))
                .groupBy(department)//각 부서 pk값을 통해 그룹화
                .fetch();
        return list;
    }

    public List<DepartmentInfoResponseDto> getDepartmentsInfo(Long councilId) {
        return jpaQueryFactory.select(Projections.constructor(
                        DepartmentInfoResponseDto.class, department.id, department.name
                ))
                .from(department)
                .groupBy(department.name)
                .where(department.council.id.eq(councilId)).fetch();
    }

    public List<PromiseContentsResponseDto> getPromises(Long councilId,Long departmentId) {
        return jpaQueryFactory.select(Projections.constructor(
                        PromiseContentsResponseDto.class, promise.content, promise.implementation
                ))
                .from(department)
                .leftJoin(department.promises, promise)
                .where(department.id.eq(departmentId))
                .where(department.council.id.eq(councilId))
                .fetch();
    }
}