package com.competitions.services;

import com.competitions.entities.*;
import com.competitions.repos.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.competitions.util.UtilFormatterClass.convertToYyyyMmDd;
import static java.util.stream.Collectors.toSet;

@Service("leadService")
@Transactional
public class LeadServiceImpl implements LeadService<CompetitionLead> {

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
        return getAllLeads().stream()
                .filter(l -> id == l.getIdPerson())
                .findAny()
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public CompetitionLead getByNickName(String nickName) {
        return getAllLeads().stream()
                .filter(l -> nickName.equals(l.getPersonNickName()))
                .findAny()
                .orElse(null);
    }

    @Override
    public CompetitionLead addNewPhone(CompetitionLead lead, String phoneNum) {
        Phone phone = Phone.builder()
                .phoneNum(phoneNum)
                .build();
        lead.getPhone().add(phone);
        phone.setPerson(lead);
        return leadRepository.save(lead);
    }

    @Override
    public CompetitionLead changePhone(CompetitionLead lead, String phoneNum, String newPhoneNum) {
        if (checkAndRemovePhone(lead, phoneNum)) return lead;
        return addNewPhone(lead, newPhoneNum);
    }

    @Override
    public CompetitionLead deleteAllPhones(CompetitionLead lead) {
        lead.getPhone().removeAll(lead.getPhone());
        return leadRepository.save(lead);
    }

    @Override
    public CompetitionLead deletePhone(CompetitionLead lead, String phoneNum) {
        if (checkAndRemovePhone(lead, phoneNum)) return lead;
        return leadRepository.save(lead);
    }

    private boolean checkAndRemovePhone(CompetitionLead lead, String phoneNum2) {
        Phone phone = lead.getPhone()
                .stream()
                .filter(p -> phoneNum2.equals(p.getPhoneNum()))
                .findAny()
                .orElse(null);
        if (phone == null) {
            return true;
        }
        lead.getPhone().remove(phone);
        return false;
    }

    @Override
    public CompetitionLead changeNickName(CompetitionLead lead, String newNickName) {
        lead.setPersonNickName(newNickName);
        return leadRepository.save(lead);
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
    public CompetitionLead changeCompetition(CompetitionLead lead, String competitionName, String newCompetitionDescription,
                                             String newCompetitionReward) {
        if (checkAndRemoveCompetition(lead, competitionName)) return lead;
        return addNewCompetition(lead, competitionName, newCompetitionDescription, newCompetitionReward);
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
    public CompetitionLead createNewPerson(Map<String, Object> specialPersonData,
                                           String leadName, String leadSurname, String leadNickName,
                                           int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                                           String... phoneNumbers) throws IllegalArgumentException {
        String date = convertToYyyyMmDd(yearOfDate, monthOfDate, dayOfDate);
        Passport passport = Passport.builder()
                .passportSeries(passportSeries)
                .passportNumber(passportNumber)
                .dateOfIssue(date)
                .build();
        Set<Phone> phones = Stream.of(phoneNumbers).map(Phone::new).collect(toSet());
        CompetitionLead lead = CompetitionLead.builder()
                .leadExperience(Double.parseDouble(specialPersonData.get("leadExperience").toString()))
                .leadCertificates(specialPersonData.get("leadCertificates").toString())
                .leadSpecialization(specialPersonData.get("leadSpecialization").toString())
                .personName(leadName)
                .personSurname(leadSurname)
                .personNickName(leadNickName)
                .passport(passport)
                .phones(Arrays.copyOf(phones.toArray(), phones.size(), Phone[].class))
                .build();
        passport.setPerson(lead);
        phones.forEach(p -> p.setPerson(lead));
        return leadRepository.save(lead);
    }

    @Override
    public void removePerson(CompetitionLead lead) {
        leadRepository.delete(lead);
    }

    @Override
    public Set<Competition> getAllCompetitions() {
        return leadRepository.getAllCompetitions();
    }

    @Override
    public Competition getCompetitionByName(String competitionName) {
        return leadRepository.getCompetitionByName(competitionName);
    }

    @Override
    public Set<Captain> getAllTeamsForCompetition(String competitionName) {
        return leadRepository.getAllTeamsForCompetition(competitionName);
    }

    @Override
    public Set<Competition> findAllCompetitionsForUser(String userNickName) {
        return leadRepository.findAllCompetitionsForUser(userNickName);
    }
}
