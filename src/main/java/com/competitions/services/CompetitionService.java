package com.competitions.services;

import com.competitions.entities.Captain;
import com.competitions.entities.Competition;

import java.util.List;
import java.util.Set;

public interface CompetitionService {

    List<Competition> findAllCompetitions(); // Guest

    Competition findCompetitionById(Integer competitionId); // Guest

    Set<Captain> findAllTeamsForCompetition(Integer competitionId); // Guest

    Set<Competition> findAllCompetitionsForUser(Integer idPerson); // Guest
}
