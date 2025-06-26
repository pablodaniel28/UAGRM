package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.entity.subjectdb;
import com.phegondev.usersmanagementsystem.repository.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepo subjectRepo;

    // Obtener todas las materias
    public List<subjectdb> getAllSubjects() {
        return subjectRepo.findAll();
    }

    // Obtener una materia por ID
    public Optional<subjectdb> getSubjectById(Integer id) {
        return subjectRepo.findById(id);
    }

    // Guardar una nueva materia
    public subjectdb saveSubject(subjectdb subject) {
        return subjectRepo.save(subject);
    }

    // Actualizar una materia existente
    public Optional<subjectdb> updateSubject(Integer id, subjectdb updatedSubject) {
        return subjectRepo.findById(id).map(existingSubject -> {
            existingSubject.setName(updatedSubject.getName());
            existingSubject.setSigla(updatedSubject.getSigla());
            existingSubject.setSemester(updatedSubject.getSemester());
            return subjectRepo.save(existingSubject);
        });
    }

    // Eliminar una materia
    public boolean deleteSubject(Integer id) {
        if (subjectRepo.existsById(id)) {
            subjectRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
