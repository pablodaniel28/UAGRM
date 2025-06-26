package com.phegondev.usersmanagementsystem.dto;

import java.time.LocalTime;

public class ScheduleDTO {
    private Integer id;
    private LocalTime start_time;
    private LocalTime end_time;
    private String day;
    private Integer teamId;

    // Constructor
    public ScheduleDTO(Integer id, LocalTime start_time, LocalTime end_time, String day, Integer teamId) {
        this.id = id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.day = day;
        this.teamId = teamId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
