package com.sejong.creativesemester.file.repository;

import com.sejong.creativesemester.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileRepository extends JpaRepository<File,Long> {

}
