package com.example.tramphatsongdidong.repository;

import com.example.tramphatsongdidong.entity.Brand;
import com.example.tramphatsongdidong.entity.TramPhat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TramRepository extends JpaRepository<TramPhat,Long> {

}
