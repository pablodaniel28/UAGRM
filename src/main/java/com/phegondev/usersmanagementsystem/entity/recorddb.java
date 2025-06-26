package com.phegondev.usersmanagementsystem.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "record")
@Data
public class recorddb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String mode;
    @Column(nullable = false)
    private String origin;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "student_id", nullable = false)
    private studentdb student;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "team_id", nullable = false)
    private teamdb team;
}
