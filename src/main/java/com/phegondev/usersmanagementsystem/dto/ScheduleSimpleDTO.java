package com.phegondev.usersmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleSimpleDTO {
    private String day;
    private String module;
    private String classroom;
    private String star_time;
    private String end_time;

}
