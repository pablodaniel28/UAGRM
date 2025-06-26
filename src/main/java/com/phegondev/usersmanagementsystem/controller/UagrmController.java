package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.dto.ScheduleDTO;
import com.phegondev.usersmanagementsystem.dto.ScheduleSimpleDTO;
import com.phegondev.usersmanagementsystem.entity.recorddb;
import com.phegondev.usersmanagementsystem.entity.scheduledb;
import com.phegondev.usersmanagementsystem.entity.studentdb;
import com.phegondev.usersmanagementsystem.service.ScheduleService;
import com.phegondev.usersmanagementsystem.service.StudentService;
import com.phegondev.usersmanagementsystem.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.phegondev.usersmanagementsystem.repository.ScheduleRepo;

import com.phegondev.usersmanagementsystem.dto.TeamCareerDTO;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/uagrm")
public class UagrmController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeamService teamService; // <-- Añadido aquí

    @Autowired
    private ScheduleRepo scheduleRepository;


    // Obtener los horarios de un grupo específico
    @GetMapping("/schedules/team/{teamId}")
    public List<ScheduleDTO> getSchedulesByTeam(@PathVariable Integer teamId) {
        return scheduleService.getSchedulesByTeam(teamId);
    }

    // Obtener un estudiante por reg
    @GetMapping("/students/{reg}")
    public ResponseEntity<studentdb> getStudentByReg(@PathVariable Integer reg) {
        Optional<studentdb> student = studentService.getStudentByReg(reg);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener los a travez del registro de un estudiante su grupos que pertenece
    @GetMapping("/students/groups/{reg}")
    public ResponseEntity<List<recorddb>> getStudentGroupsByReg(@PathVariable Integer reg) {
        List<recorddb> groups = studentService.getStudentGroupsByReg(reg);
        if (groups != null && !groups.isEmpty()) {
            return ResponseEntity.ok(groups);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //boleta inscripcion
    // Obtener los grupos a los que pertenece un estudiante con 'name' = 'inscrito'
    @GetMapping("/students/boleta/{reg}")
    public ResponseEntity<List<recorddb>> getStudentBoleta(@PathVariable Integer reg) {
        List<recorddb> groups = studentService.getStudentBoleta(reg);
        if (groups != null && !groups.isEmpty()) {
            return ResponseEntity.ok(groups);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //avance academico
    // Obtener los grupos a los que pertenece un estudiante con 'name' = 'aprobada'
    @GetMapping("/students/avance/{reg}")
    public ResponseEntity<List<recorddb>> getStudentAvance(@PathVariable Integer reg) {
        List<recorddb> groups = studentService.getStudentAvance(reg);
        if (groups != null && !groups.isEmpty()) {
            return ResponseEntity.ok(groups);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/team/{name}/subject/{sigla}/horario")
    public ResponseEntity<List<ScheduleSimpleDTO>> getSimpleHorario(
            @PathVariable("name") String teamName,
            @PathVariable("sigla") String subjectSigla) {

        List<ScheduleSimpleDTO> horarios = scheduleService.getSimpleScheduleByTeamAndSubject(teamName, subjectSigla);

        if (horarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(horarios);
    }

    // Obtener grupos de una carrera con nombre de materia y sigla
    @GetMapping("/obtenergrupocarrera/{careerId}")
    public ResponseEntity<List<TeamCareerDTO>> getTeamsByCareer(@PathVariable Integer careerId) {
        List<TeamCareerDTO> result = teamService.getTeamsByCareerId(careerId);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }
}
