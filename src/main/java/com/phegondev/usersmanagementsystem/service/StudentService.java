package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.entity.recorddb;
import com.phegondev.usersmanagementsystem.entity.studentdb;
import com.phegondev.usersmanagementsystem.repository.RecordRepo;
import com.phegondev.usersmanagementsystem.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private RecordRepo recordRepo;

    // Obtener todos los estudiantes
    public List<studentdb> getAllStudents() {
        return studentRepo.findAll();
    }

    // Obtener un estudiante por ID
    public Optional<studentdb> getStudentById(Integer id) {
        return studentRepo.findById(id);
    }

    // Guardar un nuevo estudiante
    public studentdb saveStudent(studentdb student) {
        return studentRepo.save(student);
    }

    // Actualizar un estudiante existente
    public Optional<studentdb> updateStudent(Integer id, studentdb updatedStudent) {
        return studentRepo.findById(id).map(existingStudent -> {
            existingStudent.setReg(updatedStudent.getReg());
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setCi(updatedStudent.getCi());
            existingStudent.setPhone(updatedStudent.getPhone());
            existingStudent.setEmail(updatedStudent.getEmail());
            return studentRepo.save(existingStudent);
        });
    }

    // Eliminar un estudiante por ID
    public boolean deleteStudent(Integer id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }

    //consultas
    // Método para obtener estudiante por reg
    public Optional<studentdb> getStudentByReg(Integer reg) {
        return studentRepo.findByReg(reg);
    }

    // Obtener los grupos a los que pertenece un estudiante
    public List<recorddb> getStudentGroupsByReg(Integer reg) {
        Optional<studentdb> student = studentRepo.findByReg(reg);
        if (student.isPresent()) {
            return recordRepo.findByStudent(student.get());
        }
        return null; // Si no se encuentra el estudiante
    }

    // Obtener los grupos a los que pertenece un estudiante, filtrando solo los de 'name' = 'inscrito'
    public List<recorddb> getStudentBoleta(Integer reg) {
        Optional<studentdb> student = studentRepo.findByReg(reg);
        if (student.isPresent()) {
            return recordRepo.findByStudentAndName(student.get(), "inscrito");
        }
        return null; // Si no se encuentra el estudiante
    }

    // Obtener los grupos a los que pertenece un estudiante, filtrando solo los de 'name' = 'aprobado'
    public List<recorddb> getStudentAvance(Integer reg) {
        Optional<studentdb> student = studentRepo.findByReg(reg);
        if (student.isPresent()) {
            return recordRepo.findByStudentAndName(student.get(), "aprobado");
        }
        return null; // Si no se encuentra el estudiante
    }
}
