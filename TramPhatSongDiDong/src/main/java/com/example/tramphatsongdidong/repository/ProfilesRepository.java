package com.example.tramphatsongdidong.repository;

import com.example.tramphatsongdidong.entity.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfilesRepository extends JpaRepository<Profiles,Long> {

    @Query(value = "from Profiles where name like concat('%',?1,'%') ")
    List<Profiles> findProfile(String name);
}
