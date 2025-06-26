package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.entity.managementdb;
import com.phegondev.usersmanagementsystem.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/managements")
public class ManagementController {

    @Autowired
    private ManagementService managementService;

    // Obtener todas las gestiones
    @GetMapping
    public List<managementdb> getAllManagements() {
        return managementService.getAllManagements();
    }

    // Obtener una gestión por ID
    @GetMapping("/{id}")
    public ResponseEntity<managementdb> getManagementById(@PathVariable Integer id) {
        return managementService.getManagementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva gestión
    @PostMapping
    public ResponseEntity<managementdb> createManagement(@RequestBody managementdb management) {
        managementdb newManagement = managementService.saveManagement(management);
        return ResponseEntity.ok(newManagement);
    }

    // Actualizar una gestión existente
    @PutMapping("/{id}")
    public ResponseEntity<managementdb> updateManagement(@PathVariable Integer id, @RequestBody managementdb updatedManagement) {
        return managementService.updateManagement(id, updatedManagement)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una gestión por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManagement(@PathVariable Integer id) {
        if (managementService.deleteManagement(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
