package com.example.tramphatsongdidong.repository;

import com.example.tramphatsongdidong.entity.LichSu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LichSuRepository extends JpaRepository<LichSu,Long> {
    List<LichSu>findByIdActive(Long id);
}
