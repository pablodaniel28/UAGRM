package com.phegondev.usersmanagementsystem.service;

import com.phegondev.usersmanagementsystem.entity.careerdb;
import com.phegondev.usersmanagementsystem.repository.CareerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CareerService {

    @Autowired
    private CareerRepo careerRepo;

    // Obtener todas las carreras
    public List<careerdb> getAllCareers() {
        return careerRepo.findAll();
    }

    // Obtener una carrera por ID
    public Optional<careerdb> getCareerById(Integer id) {
        return careerRepo.findById(id);
    }

    // Guardar una nueva carrera
    public careerdb saveCareer(careerdb career) {
        return careerRepo.save(career);
    }

    // Actualizar una carrera
    public Optional<careerdb> updateCareer(Integer id, careerdb updatedCareer) {
        return careerRepo.findById(id).map(existingCareer -> {
            existingCareer.setName(updatedCareer.getName());
            return careerRepo.save(existingCareer);
        });
    }

    // Eliminar una carrera
    public boolean deleteCareer(Integer id) {
        if (careerRepo.existsById(id)) {
            careerRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
