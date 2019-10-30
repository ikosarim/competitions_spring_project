package com.competitions.services;

import com.competitions.entities.CompetitionLead;
import com.competitions.entities.Person;

import java.util.List;

public interface LeadService<P extends Person> extends PersonService<P> {
    List<CompetitionLead> getAllLeads();

    CompetitionLead getLeadById(Integer id);

    CompetitionLead changeLeadExperience(CompetitionLead lead, double newLeadExperience);

    CompetitionLead changeLeadCertificate(CompetitionLead lead, String newLeadCertificate);

    CompetitionLead changeLeadSpecialization(CompetitionLead lead, String newLeadSpecialization);

    CompetitionLead addNewCompetition(CompetitionLead lead, String competitionName, String competitionDescription, String competitionReward);

    CompetitionLead changeCompetition(CompetitionLead lead, String competitionName, String competitionDescription, String competitionReward);

    CompetitionLead deleteAllCompetitions(CompetitionLead lead);

    CompetitionLead deleteCompetition(CompetitionLead lead, String competitionName);
}
