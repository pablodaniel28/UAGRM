package com.phegondev.usersmanagementsystem.controller;

import com.phegondev.usersmanagementsystem.entity.studentdb;
import com.phegondev.usersmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Obtener todos los estudiantes
    @GetMapping
    public List<studentdb> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<studentdb> getStudentById(@PathVariable Integer id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo estudiante
    @PostMapping
    public ResponseEntity<studentdb> createStudent(@RequestBody studentdb student) {
        studentdb newStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(newStudent);
    }

    // Actualizar un estudiante existente
    @PutMapping("/{id}")
    public ResponseEntity<studentdb> updateStudent(@PathVariable Integer id, @RequestBody studentdb updatedStudent) {
        return studentService.updateStudent(id, updatedStudent)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un estudiante por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        if (studentService.deleteStudent(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
