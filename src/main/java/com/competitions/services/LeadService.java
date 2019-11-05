package com.competitions.services;

import com.competitions.entities.CompetitionLead;

import java.util.List;

public interface LeadService {

    List<CompetitionLead> getAllLeads(); // Captain, Lead

    CompetitionLead getLeadById(Integer id); // Captain, Lead

    CompetitionLead changeLeadExperience(CompetitionLead lead, double newLeadExperience); // Lead

    CompetitionLead changeLeadCertificate(CompetitionLead lead, String newLeadCertificate); // Lead

    CompetitionLead changeLeadSpecialization(CompetitionLead lead, String newLeadSpecialization); // Lead

    CompetitionLead addNewCompetition(CompetitionLead lead, String competitionName, String competitionDescription, String competitionReward); // Lead

    CompetitionLead changeCompetition(CompetitionLead lead, String competitionName, String competitionDescription, String competitionReward); // Lead

    CompetitionLead deleteAllCompetitions(CompetitionLead lead); // Lead

    CompetitionLead deleteCompetition(CompetitionLead lead, String competitionName); // Lead

    CompetitionLead createNewPerson(double leadExperience, String leadCertificates, String leadSpecialization,
                                    String personName, String personSurname, String personNickName,
                                    int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                                    String... phoneNumbers) throws IllegalArgumentException; // Lead

    void removePerson(CompetitionLead member); // Lead
}
