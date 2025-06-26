package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.entity.recorddb;
import com.phegondev.usersmanagementsystem.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    // Obtener todos los registros
    @GetMapping
    public List<recorddb> getAllRecords() {
        return recordService.getAllRecords();
    }

    // Obtener un registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<recorddb> getRecordById(@PathVariable Integer id) {
        return recordService.getRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo registro
    @PostMapping
    public ResponseEntity<recorddb> createRecord(@RequestBody recorddb record) {
        return recordService.saveRecord(record)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    // Actualizar un registro existente
    @PutMapping("/{id}")
    public ResponseEntity<recorddb> updateRecord(@PathVariable Integer id, @RequestBody recorddb updatedRecord) {
        return recordService.updateRecord(id, updatedRecord)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un registro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Integer id) {
        return recordService.deleteRecord(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
