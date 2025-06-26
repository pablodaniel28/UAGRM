package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.entity.subjectdb;
import com.phegondev.usersmanagementsystem.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // Obtener todas las materias
    @GetMapping
    public List<subjectdb> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    // Obtener una materia por ID
    @GetMapping("/{id}")
    public ResponseEntity<subjectdb> getSubjectById(@PathVariable Integer id) {
        return subjectService.getSubjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear una nueva materia
    @PostMapping
    public ResponseEntity<subjectdb> createSubject(@RequestBody subjectdb subject) {
        subjectdb newSubject = subjectService.saveSubject(subject);
        return ResponseEntity.ok(newSubject);
    }

    // Actualizar una materia existente
    @PutMapping("/{id}")
    public ResponseEntity<subjectdb> updateSubject(@PathVariable Integer id, @RequestBody subjectdb updatedSubject) {
        return subjectService.updateSubject(id, updatedSubject)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar una materia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Integer id) {
        if (subjectService.deleteSubject(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
