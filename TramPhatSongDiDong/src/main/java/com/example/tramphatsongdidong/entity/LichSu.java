package com.example.tramphatsongdidong.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
public class LichSu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ngayCapNhap",unique = false)
    private String ngayEdit;
    @Column(name = "idUserName")
    private Long idUserName;
    @Column(name = "idActive")
    private Long idActive;

}
