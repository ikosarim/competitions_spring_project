package com.competitions.services;

import com.competitions.entities.CompetitionLead;

import java.util.List;

public interface LeadService {

    List<CompetitionLead> getAllLeads(); // Captain, Lead

    CompetitionLead getLeadById(Integer id); // Captain, Lead

    CompetitionLead changeLeadExperience(CompetitionLead lead, double newLeadExperience); // +Controller -Lead

    CompetitionLead changeLeadCertificate(CompetitionLead lead, String newLeadCertificate); // +Controller Lead

    CompetitionLead changeLeadSpecialization(CompetitionLead lead, String newLeadSpecialization); // +Controller Lead

    CompetitionLead addNewCompetition(CompetitionLead lead, String competitionName, String competitionDescription, String competitionReward); // Lead

    CompetitionLead changeCompetition(CompetitionLead lead, String competitionName, String competitionDescription, String competitionReward); // Lead

    CompetitionLead deleteAllCompetitions(CompetitionLead lead); // Lead

    CompetitionLead deleteCompetition(CompetitionLead lead, String competitionName); // Lead

    CompetitionLead createNewPerson(double leadExperience, String leadCertificates, String leadSpecialization,
                                    String personName, String personSurname, String personNickName,
                                    int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                                    String... phoneNumbers) throws IllegalArgumentException; // +Controller -Lead

    void removePerson(CompetitionLead member); // +Controller -Lead
}
