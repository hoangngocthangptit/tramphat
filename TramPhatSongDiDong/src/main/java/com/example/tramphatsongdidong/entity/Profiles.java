package com.example.tramphatsongdidong.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Profiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",unique = true)
    private String name;
    @Column(name = "moTa")
    private String moTa;
    @Column(name = "nguoiTao")
    private String nguoiTao;
    @Column(name = "ngayTao")
    private String ngayTao;
    @Column(name = "ngayCapNhat")
    private String ngayCapNhat;
    @JsonIgnore
    @Transient
    @ManyToMany(mappedBy = "listProfiles")
    private List<TramPhat> listTramPhat;

}
