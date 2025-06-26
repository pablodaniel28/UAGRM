package com.phegondev.usersmanagementsystem.repository;

import com.phegondev.usersmanagementsystem.entity.scheduledb;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepo extends JpaRepository<scheduledb, Integer> {
    // Obtener los horarios de un equipo específico
    List<scheduledb> findByTeamId(Integer teamId);
    @Query("SELECT s FROM scheduledb s WHERE s.team.name = :teamName AND s.team.subject.sigla = :sigla")
    List<scheduledb> findByTeamNameAndSubjectSigla(@Param("teamName") String teamName, @Param("sigla") String sigla);
}
