package com.phegondev.usersmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subject")
@Data
public class subjectdb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String sigla;
    @Column(nullable = false)
    private Integer semester;
}