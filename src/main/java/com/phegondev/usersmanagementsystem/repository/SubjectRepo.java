package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.entity.subjectdb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepo extends JpaRepository<subjectdb, Integer> {
}
