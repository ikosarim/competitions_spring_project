package com.competitions.services;

import com.competitions.entities.Captain;
import com.competitions.entities.Competition;
import com.competitions.repos.CompetitionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@Service
@Transactional(readOnly = true)
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    CompetitionsRepository competitionsRepository;

    @Override
    public List<Competition> findAllCompetitions() {
        return competitionsRepository.findAll();
    }

    @Override
    public Competition findCompetitionById(Integer competitionId) {
        return competitionsRepository.findById(competitionId).orElse(null);
    }

    @Override
    public Set<Captain> findAllTeamsForCompetition(Integer competitionId) {
        return competitionsRepository.findAllCaptainsForCompetition(competitionId);
    }

    @Override
    public Set<Competition> findAllCompetitionsForUser(Integer idPerson) {
        return competitionsRepository.findAllCompetitionsForUser(idPerson);
    }
}
