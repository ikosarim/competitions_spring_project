package com.competitions.repos;

import com.competitions.entities.Captain;
import com.competitions.entities.Competition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PersonsRepository {

    @Query("select comp from competition comp")
    Set<Competition> getAllCompetitions();

    @Query("select comp from competition comp where comp.competitionName = :competition_name")
    Competition getCompetitionByName(@Param("competition_name") String competitionName);

    @Query("select cap from captain cap " +
            "inner join cap.competitionSet comp " +
            "where comp.competitionName = :competition_name")
    Set<Captain> getAllTeamsForCompetition(@Param("competition_name") String competitionName);

    @Query("select comp from competition comp " +
            "inner join comp.captainSet cap " +
            "where cap.personNickName = :person_nick_name")
    Set<Competition> findAllCompetitionsForUser(@Param("person_nick_name") String userNickName);
}
