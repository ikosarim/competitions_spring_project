package com.competitions.repos;

import com.competitions.entities.Captain;
import com.competitions.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CompetitionsRepository extends JpaRepository<Competition, Integer> {

    @Query("select cap from captain cap " +
            "inner join cap.competitionSet comp " +
            "where comp.idCompetition = :id_competition")
    Set<Captain> findAllCaptainsForCompetition(@Param("id_competition") Integer competitionId);

    @Query("select comp from competition comp " +
            "inner join comp.captainSet cap " +
            "where cap.idPerson = :id_person")
    Set<Competition> findAllCompetitionsForUser(@Param("id_person") Integer personId);
}
