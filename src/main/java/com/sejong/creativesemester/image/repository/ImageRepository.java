package com.sejong.creativesemester.image.repository;

import com.sejong.creativesemester.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
