package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.entity.careerdb;
import com.phegondev.usersmanagementsystem.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/careers")

public class CareerController {

    @Autowired
    private CareerService careerService;

    // Obtener todas las carreras
    @GetMapping
    public List<careerdb> getAllCareers() {
        return careerService.getAllCareers();
    }

    // Obtener una carrera por ID
    @GetMapping("/{id}")
    public ResponseEntity<careerdb> getCareerById(@PathVariable Integer id) {
        return careerService.getCareerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva carrera
    @PostMapping
    public ResponseEntity<careerdb> createCareer(@RequestBody careerdb career) {
        careerdb newCareer = careerService.saveCareer(career);
        return ResponseEntity.ok(newCareer);
    }

    // Actualizar una carrera existente
    @PutMapping("/{id}")
    public ResponseEntity<careerdb> updateCareer(@PathVariable Integer id, @RequestBody careerdb updatedCareer) {
        return careerService.updateCareer(id, updatedCareer)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una carrera por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCareer(@PathVariable Integer id) {
        if (careerService.deleteCareer(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
