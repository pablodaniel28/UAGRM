package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.entity.studentdb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<studentdb, Integer> {

    // Buscar estudiante por el número de registro (reg)
    Optional<studentdb> findByReg(Integer reg);


}
