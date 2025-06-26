package com.phegondev.usersmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class studentdb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer reg;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private Integer ci;
    @Column(nullable = true)
    private Integer phone;
    @Column(nullable = true)
    private String email;
}
