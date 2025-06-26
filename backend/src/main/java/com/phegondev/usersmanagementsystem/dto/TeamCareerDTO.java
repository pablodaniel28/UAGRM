package com.phegondev.usersmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamCareerDTO {
    private Integer teamId;
    private String teamName;
    private String subjectName;
    private String subjectSigla;
}