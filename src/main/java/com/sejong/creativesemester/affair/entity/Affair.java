package com.sejong.creativesemester.affair.entity;

import com.sejong.creativesemester.common.domain.BaseTimeEntity;
import com.sejong.creativesemester.file.entity.File;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Affair extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String restMoney;
    private String usedMoney;
    private String title;
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private File file;

    public void updateFile(File file){
        this.file = file;
    }
}
