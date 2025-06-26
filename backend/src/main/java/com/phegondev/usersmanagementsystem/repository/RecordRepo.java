package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.entity.recorddb;
import com.phegondev.usersmanagementsystem.entity.studentdb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepo extends JpaRepository<recorddb, Integer> {
    // Método para encontrar los registros de un estudiante por su entidad 'studentdb'
    List<recorddb> findByStudent(studentdb student);
    // Método para encontrar los registros de un estudiante por su entidad 'studentdb' y donde el nombre sea 'inscrito'
    List<recorddb> findByStudentAndName(studentdb student, String name);
}