package com.competitions.services;

import com.competitions.entities.Competition;

import java.util.List;
import java.util.Set;

public interface CompetitionService {

    List<Competition> findAllCompetitions(); // +Controller -Guest

    Competition findCompetitionById(Integer competitionId); // +Controller -Guest

    Set<Competition> findAllCompetitionsForUser(Integer idPerson); // +Controller -Guest
}
