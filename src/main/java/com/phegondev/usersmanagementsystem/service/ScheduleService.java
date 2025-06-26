package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.dto.ScheduleDTO;
import com.phegondev.usersmanagementsystem.dto.ScheduleSimpleDTO;
import com.phegondev.usersmanagementsystem.entity.scheduledb;
import com.phegondev.usersmanagementsystem.repository.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepo scheduleRepo;

    // Obtener todos los horarios
    public List<scheduledb> getAllSchedules() {
        return scheduleRepo.findAll();
    }

    // Obtener un horario por ID
    public Optional<scheduledb> getScheduleById(Integer id) {
        return scheduleRepo.findById(id);
    }

    // Guardar un nuevo horario
    public scheduledb saveSchedule(scheduledb schedule) {
        return scheduleRepo.save(schedule);
    }

    // Actualizar un horario existente
    public Optional<scheduledb> updateSchedule(Integer id, scheduledb updatedSchedule) {
        return scheduleRepo.findById(id).map(existingSchedule -> {
            existingSchedule.setStar_time(updatedSchedule.getStar_time());
            existingSchedule.setEnd_time(updatedSchedule.getEnd_time());
            existingSchedule.setDay(updatedSchedule.getDay());
            existingSchedule.setTeam(updatedSchedule.getTeam());
            return scheduleRepo.save(existingSchedule);
        });
    }

    // Eliminar un horario por ID
    public boolean deleteSchedule(Integer id) {
        if (scheduleRepo.existsById(id)) {
            scheduleRepo.deleteById(id);
            return true;
        }
        return false;
    }

    //consultas
    // Obtener los horarios de un equipo específico
    // Obtener los horarios de un equipo específico
    public List<ScheduleDTO> getSchedulesByTeam(Integer teamId) {
        return scheduleRepo.findByTeamId(teamId).stream()
                .map(schedule -> new ScheduleDTO(
                        schedule.getId(),
                        schedule.getStar_time(),
                        schedule.getEnd_time(),
                        schedule.getDay(),
                        schedule.getTeam().getId())) // Solo devuelve el ID del equipo
                .collect(Collectors.toList());
    }

    public List<ScheduleSimpleDTO> getSimpleScheduleByTeamAndSubject(String teamName, String subjectSigla) {
        List<scheduledb> horarios = scheduleRepo.findByTeamNameAndSubjectSigla(teamName, subjectSigla);

        return horarios.stream()
                .map(s -> new ScheduleSimpleDTO(
                        s.getDay(),
                        s.getModule(),
                        s.getClassroom(),
                        s.getStar_time().toString(),
                        s.getEnd_time().toString()
                ))
                .collect(Collectors.toList());
    }
}
