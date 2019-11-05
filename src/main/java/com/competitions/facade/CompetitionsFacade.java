package com.competitions.facade;

import com.competitions.entities.Competition;
import com.competitions.services.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionsFacade {

    @Autowired
    CompetitionService competitionService;

    public List<Competition> findAllCompetitions() {
        return competitionService.findAllCompetitions();
    }

    public Competition getCompetitionInfo(Integer competitionId) {
        return competitionService.findCompetitionById(competitionId);
    }
}
