package com.example.tramphatsongdidong.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",unique = true)
    private  String name;

}
