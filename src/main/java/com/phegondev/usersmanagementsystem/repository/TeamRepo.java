package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.dto.TeamCareerDTO;
import com.phegondev.usersmanagementsystem.entity.teamdb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepo extends JpaRepository<teamdb, Integer> {
    @Query("SELECT new com.phegondev.usersmanagementsystem.dto.TeamCareerDTO(t.id, t.name, s.name, s.sigla) " +
            "FROM teamdb t JOIN t.subject s WHERE t.career.id = :careerId")
    List<TeamCareerDTO> findTeamsByCareerId(Integer careerId);
}