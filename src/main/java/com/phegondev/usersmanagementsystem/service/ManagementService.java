package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.entity.managementdb;
import com.phegondev.usersmanagementsystem.repository.ManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagementService {

    @Autowired
    private ManagementRepo managementRepo;

    // Obtener todas las gestiones
    public List<managementdb> getAllManagements() {
        return managementRepo.findAll();
    }

    // Obtener una gestión por ID
    public Optional<managementdb> getManagementById(Integer id) {
        return managementRepo.findById(id);
    }

    // Guardar una nueva gestión
    public managementdb saveManagement(managementdb management) {
        return managementRepo.save(management);
    }

    // Actualizar una gestión existente
    public Optional<managementdb> updateManagement(Integer id, managementdb updatedManagement) {
        return managementRepo.findById(id).map(existingManagement -> {
            existingManagement.setName(updatedManagement.getName());
            return managementRepo.save(existingManagement);
        });
    }

    // Eliminar una gestión por ID
    public boolean deleteManagement(Integer id) {
        if (managementRepo.existsById(id)) {
            managementRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
