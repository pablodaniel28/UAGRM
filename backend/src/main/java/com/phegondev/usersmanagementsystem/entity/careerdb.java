package com.phegondev.usersmanagementsystem.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "career")
@Data
public class careerdb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cod;
    @Column(nullable = false)
    private String locality;
}

