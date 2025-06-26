package com.phegondev.usersmanagementsystem.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@Data
public class scheduledb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private LocalTime  star_time;
    @Column(nullable = false)
    private LocalTime end_time;
    @Column(nullable = false)
    private String day;
    @Column(nullable = false)
    private String module;
    @Column(nullable = false)
    private String classroom;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "team_id", nullable = false)
    private teamdb team;
}
