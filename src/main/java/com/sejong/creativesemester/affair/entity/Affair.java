package com.sejong.creativesemester.affair.entity;

import com.sejong.creativesemester.file.entity.File;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Affair {
    @Id
    @GeneratedValue
    private Long id;

    private String restMoney;
    private String usedMoney;

    @OneToOne
    @JoinColumn(name = "file_id")
    private File file;

    public void updateFile(File file){
        this.file = file;
    }
}
