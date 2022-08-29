package com.example.tramphatsongdidong.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity

public class Brand {

    //: BrandID, BrandName, thông tin mô tả, người tạo, ngày tạo, ngày cập nhật gần nhất

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",unique = true)
    private String name;
    @Column(name = "moTa",unique = false)
    private String moTa;
    @Column(name = "nguoiTao",unique = false)
    private String nguoiTao;
    @Column(name = "ngayTao",unique = false)
    private String ngayTao;
    @Column(name = "ngayCapNhat")
    private String ngayCapNhat;
    @JsonIgnore
    @OneToMany(mappedBy = "idBrand")
    private List<TramPhat> tramPhats;


}
