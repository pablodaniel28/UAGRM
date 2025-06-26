package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.entity.scheduledb;
import com.phegondev.usersmanagementsystem.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Obtener todos los horarios
    @GetMapping
    public List<scheduledb> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // Obtener un horario por ID
    @GetMapping("/{id}")
    public ResponseEntity<scheduledb> getScheduleById(@PathVariable Integer id) {
        Optional<scheduledb> schedule = scheduleService.getScheduleById(id);
        return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo horario
    @PostMapping
    public scheduledb createSchedule(@RequestBody scheduledb schedule) {
        return scheduleService.saveSchedule(schedule);
    }

    // Actualizar un horario existente
    @PutMapping("/{id}")
    public ResponseEntity<scheduledb> updateSchedule(@PathVariable Integer id, @RequestBody scheduledb updatedSchedule) {
        Optional<scheduledb> schedule = scheduleService.updateSchedule(id, updatedSchedule);
        return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un horario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer id) {
        return scheduleService.deleteSchedule(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }




}
