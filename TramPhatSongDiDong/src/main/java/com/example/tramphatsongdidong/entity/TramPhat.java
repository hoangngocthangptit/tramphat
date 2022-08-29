package com.example.tramphatsongdidong.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class TramPhat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ngayTao")
    private String ngayTao;
    //Mã Trạm, ngày tạo, brandID, trạng thái, địa chỉ.
    @Column(name = "trangThai",unique = false)
    private String trangThai;
    @Column(name = "diaChi",unique= false)
    private String diaChi;
    @ManyToMany
    @JoinTable(name = "Tram_Profiles",
        joinColumns = @JoinColumn(name = "id_tram"),
        inverseJoinColumns = @JoinColumn(name = "id_profiles"))
    private List<Profiles> listProfiles;
    @Column(name = "idBrand",unique= false)
    private  Long idBrand;


}
