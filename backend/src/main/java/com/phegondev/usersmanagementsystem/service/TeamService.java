package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.dto.TeamCareerDTO;
import com.phegondev.usersmanagementsystem.entity.teamdb;
import com.phegondev.usersmanagementsystem.entity.managementdb;
import com.phegondev.usersmanagementsystem.entity.careerdb;
import com.phegondev.usersmanagementsystem.entity.subjectdb;
import com.phegondev.usersmanagementsystem.repository.TeamRepo;
import com.phegondev.usersmanagementsystem.repository.ManagementRepo;
import com.phegondev.usersmanagementsystem.repository.CareerRepo;
import com.phegondev.usersmanagementsystem.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private ManagementRepo managementRepo;

    @Autowired
    private CareerRepo careerRepo;

    @Autowired
    private SubjectRepo subjectRepo;

    // Implementación correcta del método para obtener equipos por carrera
    public List<TeamCareerDTO> getTeamsByCareerId(Integer careerId) {
        return teamRepo.findTeamsByCareerId(careerId);
    }

    // Obtener todos los equipos
    public List<teamdb> getAllTeams() {
        return teamRepo.findAll();
    }

    // Obtener un equipo por ID
    public Optional<teamdb> getTeamById(Integer id) {
        return teamRepo.findById(id);
    }

    // Guardar un nuevo equipo con entidades administradas por Hibernate
    @Transactional
    public teamdb saveTeam(teamdb team) {
        managementdb management = managementRepo.findById(team.getManagement().getId())
                .orElseThrow(() -> new RuntimeException("Management not found"));
        careerdb career = careerRepo.findById(team.getCareer().getId())
                .orElseThrow(() -> new RuntimeException("Career not found"));
        subjectdb subject = subjectRepo.findById(team.getSubject().getId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        team.setManagement(management);
        team.setCareer(career);
        team.setSubject(subject);

        return teamRepo.save(team);
    }

    // Actualizar un equipo existente
    @Transactional
    public Optional<teamdb> updateTeam(Integer id, teamdb updatedTeam) {
        return teamRepo.findById(id).map(existingTeam -> {
            existingTeam.setName(updatedTeam.getName());
            existingTeam.setState(updatedTeam.getState());

            managementdb management = managementRepo.findById(updatedTeam.getManagement().getId())
                    .orElseThrow(() -> new RuntimeException("Management not found"));
            careerdb career = careerRepo.findById(updatedTeam.getCareer().getId())
                    .orElseThrow(() -> new RuntimeException("Career not found"));
            subjectdb subject = subjectRepo.findById(updatedTeam.getSubject().getId())
                    .orElseThrow(() -> new RuntimeException("Subject not found"));

            existingTeam.setManagement(management);
            existingTeam.setCareer(career);
            existingTeam.setSubject(subject);

            return teamRepo.save(existingTeam);
        });
    }

    // Eliminar un equipo por ID
    public boolean deleteTeam(Integer id) {
        if (teamRepo.existsById(id)) {
            teamRepo.deleteById(id);
            return true;
        }
        return false;
    }
}