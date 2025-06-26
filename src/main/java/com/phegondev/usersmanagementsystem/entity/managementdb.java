package com.phegondev.usersmanagementsystem.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "management")
@Data
public class managementdb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
}
