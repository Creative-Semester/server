package com.sejong.creativesemester.promise.entity;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.department.entity.Department;
import lombok.Getter;

import javax.persistence.*;
@Getter
@Entity(name = "PROMISE_TABLE")
public class Promise extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private boolean implementation=false;
    public boolean implementPromise(){
        if(implementation==true){
            implementation = false;
            return false;
        }
        implementation = true;
        return true;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

}