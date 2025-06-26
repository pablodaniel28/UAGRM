package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.entity.careerdb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepo extends JpaRepository<careerdb, Integer> {
}
