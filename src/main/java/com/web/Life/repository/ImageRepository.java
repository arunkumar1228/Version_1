package com.web.Life.repository;


import com.web.Life.entity.QbImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<QbImage, Integer> {
}
