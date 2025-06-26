package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.entity.managementdb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementRepo extends JpaRepository<managementdb, Integer> {
}
