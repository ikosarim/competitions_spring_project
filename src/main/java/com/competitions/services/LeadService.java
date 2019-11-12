package com.competitions.services;

import com.competitions.entities.CompetitionLead;
import com.competitions.entities.UserRoleEnum;

import java.util.List;

public interface LeadService {

    List<CompetitionLead> getAllLeads(); // +Controller -(Captain, Lead)

    CompetitionLead getLeadById(Integer id); // +Controller -(Captain, Lead)

    CompetitionLead changeLeadExperience(CompetitionLead lead, double newLeadExperience); // +Controller -Lead

    CompetitionLead changeLeadCertificate(CompetitionLead lead, String newLeadCertificate); // +Controller -Lead

    CompetitionLead changeLeadSpecialization(CompetitionLead lead, String newLeadSpecialization); // +Controller -Lead

    CompetitionLead addNewCompetition(CompetitionLead lead, String competitionName, String competitionDescription,
                                      String competitionReward); // +Controller -Lead

    CompetitionLead changeCompetition(CompetitionLead lead, String competitionName, String competitionDescription,
                                      String competitionReward); // +Controller -Lead

    CompetitionLead deleteAllCompetitions(CompetitionLead lead); //  +Controller -Lead

    CompetitionLead deleteCompetition(CompetitionLead lead, String competitionName); //  +Controller -Lead

    CompetitionLead createNewPerson(String password, UserRoleEnum role,
                                    double leadExperience, String leadCertificates, String leadSpecialization,
                                    String personName, String personSurname, String personNickName,
                                    int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                                    String... phoneNumbers) throws IllegalArgumentException; // +Controller -Lead

    void removePerson(CompetitionLead member); // +Controller -Lead
}
