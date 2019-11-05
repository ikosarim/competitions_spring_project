package com.competitions.services;

import com.competitions.entities.Competition;
import com.competitions.entities.CompetitionLead;
import com.competitions.entities.Passport;
import com.competitions.entities.Phone;
import com.competitions.repos.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.competitions.util.UtilFormatterClass.convertToYyyyMmDd;
import static java.util.stream.Collectors.toSet;

@Repository
@Service
@Transactional
public class LeadServiceImpl implements LeadService {

    @Autowired
    LeadRepository leadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CompetitionLead> getAllLeads() {
        return leadRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CompetitionLead getLeadById(Integer id) {
        return leadRepository.findById(id).orElse(null);
    }

    @Override
    public CompetitionLead changeLeadExperience(CompetitionLead lead, double newLeadExperience) {
        lead.setLeadExperience(newLeadExperience);
        return leadRepository.save(lead);
    }

    @Override
    public CompetitionLead changeLeadCertificate(CompetitionLead lead, String newLeadCertificate) {
        lead.setLeadCertificates(newLeadCertificate);
        return leadRepository.save(lead);
    }

    @Override
    public CompetitionLead changeLeadSpecialization(CompetitionLead lead, String newLeadSpecialization) {
        lead.setLeadSpecialization(newLeadSpecialization);
        return leadRepository.save(lead);
    }

    @Override
    public CompetitionLead addNewCompetition(CompetitionLead lead, String competitionName, String competitionDescription,
                                             String competitionReward) {
        Competition competition = Competition.builder()
                .competitionName(competitionName)
                .competitionDescription(competitionDescription)
                .competitionReward(competitionReward)
                .build();
        lead.getCompetitions().add(competition);
        competition.setCompetitionLead(lead);
        return leadRepository.save(lead);
    }

    @Override
    public CompetitionLead changeCompetition(CompetitionLead lead, String competitionName, String competitionDescription,
                                             String competitionReward) {
        if (checkAndRemoveCompetition(lead, competitionName)) return lead;
        return addNewCompetition(lead, competitionName, competitionDescription, competitionReward);
    }

    @Override
    public CompetitionLead deleteAllCompetitions(CompetitionLead lead) {
        Set<Competition> competitions = lead.getCompetitions();
        lead.getCompetitions().removeAll(competitions);
        return leadRepository.save(lead);
    }

    @Override
    public CompetitionLead deleteCompetition(CompetitionLead lead, String competitionName) {
        if (checkAndRemoveCompetition(lead, competitionName)) return lead;
        return leadRepository.save(lead);
    }

    private boolean checkAndRemoveCompetition(CompetitionLead lead, String competitionName) {
        Competition competition = lead.getCompetitions()
                .stream()
                .filter(comp -> competitionName.equals(comp.getCompetitionName()))
                .findAny()
                .orElse(null);
        if (competition == null) {
            return true;
        }
        lead.getCompetitions().remove(competition);
        return false;
    }

    @Override
    public CompetitionLead createNewPerson(double leadExperience, String leadCertificates, String leadSpecialization,
                                           String personName, String personSurname, String personNickName,
                                           int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                                           String... phoneNumbers) throws IllegalArgumentException {
        String date = convertToYyyyMmDd(yearOfDate, monthOfDate, dayOfDate);
        Passport passport = createPassport(passportSeries, passportNumber, date);
        Set<Phone> phones = Stream.of(phoneNumbers).map(Phone::new).collect(toSet());
        CompetitionLead lead = createLead(leadExperience, leadCertificates, leadSpecialization,
                personName, personSurname, personNickName, passport, phones);
        passport.setPerson(lead);
        phones.forEach(p -> p.setPerson(lead));
        return leadRepository.save(lead);
    }

    private CompetitionLead createLead(double leadExperience, String leadCertificates, String leadSpecialization,
                                       String personName, String personSurname, String personNickName,
                                       Passport passport, Set<Phone> phones) {
        return CompetitionLead.builder()
                .leadExperience(leadExperience)
                .leadCertificates(leadCertificates)
                .leadSpecialization(leadSpecialization)
                .personName(personName)
                .personSurname(personSurname)
                .personNickName(personNickName)
                .passport(passport)
                .phones(Arrays.copyOf(phones.toArray(), phones.size(), Phone[].class))
                .build();
    }

    private Passport createPassport(int passportSeries, int passportNumber, String date) {
        return Passport.builder()
                .passportSeries(passportSeries)
                .passportNumber(passportNumber)
                .dateOfIssue(date)
                .build();
    }

    @Override
    public void removePerson(CompetitionLead lead) {
        leadRepository.delete(lead);
    }
}
