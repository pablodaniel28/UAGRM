package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.entity.teamdb;
import com.phegondev.usersmanagementsystem.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    // Obtener todos los equipos
    @GetMapping
    public List<teamdb> getAllTeams() {
        return teamService.getAllTeams();
    }

    // Obtener un equipo por ID
    @GetMapping("/{id}")
    public ResponseEntity<teamdb> getTeamById(@PathVariable Integer id) {
        return teamService.getTeamById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo equipo
    @PostMapping
    public ResponseEntity<teamdb> createTeam(@RequestBody teamdb team) {
        teamdb newTeam = teamService.saveTeam(team);
        return ResponseEntity.ok(newTeam);
    }

    // Actualizar un equipo existente
    @PutMapping("/{id}")
    public ResponseEntity<teamdb> updateTeam(@PathVariable Integer id, @RequestBody teamdb updatedTeam) {
        return teamService.updateTeam(id, updatedTeam)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un equipo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Integer id) {
        if (teamService.deleteTeam(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
