package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.entity.recorddb;
import com.phegondev.usersmanagementsystem.entity.studentdb;
import com.phegondev.usersmanagementsystem.entity.teamdb;
import com.phegondev.usersmanagementsystem.repository.RecordRepo;
import com.phegondev.usersmanagementsystem.repository.StudentRepo;
import com.phegondev.usersmanagementsystem.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    private RecordRepo recordRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TeamRepo teamRepo;

    // Obtener todos los registros
    public List<recorddb> getAllRecords() {
        return recordRepo.findAll();
    }

    // Obtener un registro por ID
    public Optional<recorddb> getRecordById(Integer id) {
        return recordRepo.findById(id);
    }

    // Guardar un nuevo registro
    public Optional<recorddb> saveRecord(recorddb record) {
        if (studentRepo.existsById(record.getStudent().getId()) &&
                teamRepo.existsById(record.getTeam().getId())) {
            return Optional.of(recordRepo.save(record));
        }
        return Optional.empty(); // Evita guardar si student o team no existen
    }

    // Actualizar un registro existente
    public Optional<recorddb> updateRecord(Integer id, recorddb updatedRecord) {
        return recordRepo.findById(id).map(existingRecord -> {
            existingRecord.setName(updatedRecord.getName());
            existingRecord.setMode(updatedRecord.getMode());
            existingRecord.setOrigin(updatedRecord.getOrigin());

            // ✅ Buscar el estudiante completo
            studentdb fullStudent = studentRepo.findById(updatedRecord.getStudent().getId())
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
            existingRecord.setStudent(fullStudent);

            // ✅ Buscar el grupo completo
            teamdb fullTeam = teamRepo.findById(updatedRecord.getTeam().getId())
                    .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
            existingRecord.setTeam(fullTeam);

            return recordRepo.save(existingRecord);
        });
    }

    // Eliminar un registro por ID
    public boolean deleteRecord(Integer id) {
        if (recordRepo.existsById(id)) {
            recordRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
