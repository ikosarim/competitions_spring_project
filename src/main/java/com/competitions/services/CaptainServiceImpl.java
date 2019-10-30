package com.competitions.services;

import com.competitions.entities.*;
import com.competitions.repos.CaptainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.competitions.util.UtilFormatterClass.convertToYyyyMmDd;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service("captainService")
@Transactional
public class CaptainServiceImpl implements CaptainService<Captain> {

    @Autowired
    CaptainRepository captainRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Captain> getAllCaptains() {
        return captainRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Captain getCaptainById(Integer id) {
        return getAllCaptains().stream()
                .filter(c -> c.getIdPerson() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Captain getByNickName(String nickName) {
        return getAllCaptains().stream()
                .filter(c -> nickName.equals(c.getPersonNickName()))
                .findAny()
                .orElse(null);
    }

    @Override
    public Captain addNewPhone(Captain captain, String phoneNum) {
        Phone phone = Phone.builder()
                .phoneNum(phoneNum)
                .build();
        captain.getPhone().add(phone);
        phone.setPerson(captain);
        return captainRepository.save(captain);
    }

    @Override
    public Captain changePhone(Captain captain, String phoneNum, String newPhoneNum) {
        if (checkAndRemovePhone(captain, phoneNum)) return captain;
        return addNewPhone(captain, newPhoneNum);
    }

    @Override
    public Captain deleteAllPhones(Captain captain) {
        captain.getPhone().removeAll(captain.getPhone());
        return captainRepository.save(captain);
    }

    @Override
    public Captain deletePhone(Captain captain, String phoneNum) {
        if (checkAndRemovePhone(captain, phoneNum)) return captain;
        return captainRepository.save(captain);
    }

    private boolean checkAndRemovePhone(Captain captain, String phoneNum) {
        Phone phone = captain.getPhone()
                .stream()
                .filter(p -> phoneNum.equals(p.getPhoneNum()))
                .findAny()
                .orElse(null);
        if (phone == null) {
            return true;
        }
        captain.getPhone().remove(phone);
        return false;
    }

    @Override
    public Captain changeNickName(Captain captain, String newNickName) {
        captain.setPersonNickName(newNickName);
        return captainRepository.save(captain);
    }

    @Override
    public Captain changeTeamName(Captain captain, String newTeamName) {
        captain.setCaptainTeamName(newTeamName);
        return captainRepository.save(captain);
    }

    @Override
    public Captain changeExperience(Captain captain, double newExperience) {
        captain.setCaptainExperience(newExperience);
        return captainRepository.save(captain);
    }

    @Override
    public Captain createNewPerson(Map<String, Object> specialPersonData,
                                   String personName, String personSurname, String personNickName,
                                   int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                                   String... phoneNumbers) throws IllegalArgumentException {
        String date = convertToYyyyMmDd(yearOfDate, monthOfDate, dayOfDate);
        Passport passport = Passport.builder()
                .passportSeries(passportSeries)
                .passportNumber(passportNumber)
                .dateOfIssue(date)
                .build();
        Set<Phone> phones = Stream.of(phoneNumbers).map(Phone::new).collect(toSet());
        Captain captain = Captain.builder()
                .captainExperience(Double.parseDouble(specialPersonData.get("captainExperience").toString()))
                .captainTeamName(specialPersonData.get("captainTeamName").toString())
                .personName(personName)
                .personNickName(personNickName)
                .personSurname(personSurname)
                .passport(passport)
                .phones(Arrays.copyOf(phones.toArray(), phones.size(), Phone[].class))
                .build();
        passport.setPerson(captain);
        phones.forEach(p -> p.setPerson(captain));
        return captainRepository.save(captain);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<RequestForEnter> getAllRequestsToCaptain(Captain captain) {
        return captain.getRequestsForEnter();
    }

    @Override
    public Captain showRequestToCaptain(Captain captain, Member member) {
        List<RequestForEnter> requestsForEnters = getAllRequestsToCaptain(captain).stream()
                .filter(r -> member.getIdPerson().equals(r.getMember().getIdPerson()))
                .collect(toList());
        requestsForEnters.get(0).setRequestStatus(true);
        return captainRepository.save(captain);
    }

    @Override
    public Captain acceptMemberEnterToCaptain(Captain captain, Member member) {
        RequestForEnter requestForEnter = getAllRequestsToCaptain(captain).stream()
                .filter(rfe -> member.equals(rfe.getMember()))
                .findAny()
                .orElseThrow();
        captain.getRequestsForEnter().remove(requestForEnter);
        member.getRequestsForEnter().removeAll(member.getRequestsForEnter());
        captain.getMembers().add(member);
        member.setCaptain(captain);
        return captainRepository.save(captain);
    }

    @Override
    public Captain declineMemberEnterToCaptain(Captain captain, Member member) {
        RequestForEnter requestForEnter = getAllRequestsToCaptain(captain).stream()
                .filter(rfe -> member.equals(rfe.getMember()))
                .findAny()
                .orElseThrow();
        member.getRequestsForEnter().remove(requestForEnter);
        captain.getRequestsForEnter().remove(requestForEnter);
        return captainRepository.save(captain);
    }

    @Override
    public Captain deleteMemberFromCaptain(Captain captain, Member member) {
        captain.getMembers().remove(member);
        return captainRepository.save(captain);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Competition> getAllCompetitionsForUser(Captain captain) {
        return captainRepository.findAllCompetitionsForUser(captain.getPersonNickName());
    }

    @Override
    public Captain takePartInTheCompetition(Captain captain, Competition competition) {
        captain.getCompetitionSet().add(competition);
        return captainRepository.save(captain);
    }

    @Override
    public Captain leaveTheCompetition(Captain captain, Competition competition) {
        captain.getCompetitionSet().remove(competition);
        return captainRepository.save(captain);
    }

    @Override
    public void removePerson(Captain captain) {
        captainRepository.delete(captain);
    }

    @Override
    public Set<Competition> getAllCompetitions() {
        return captainRepository.getAllCompetitions();
    }

    @Override
    public Competition getCompetitionByName(String competitionName) {
        return captainRepository.getCompetitionByName(competitionName);
    }

    @Override
    public Set<Captain> getAllTeamsForCompetition(String competitionName) {
        return captainRepository.getAllTeamsForCompetition(competitionName);
    }

    @Override
    public Set<Competition> findAllCompetitionsForUser(String userNickName) {
        return captainRepository.findAllCompetitionsForUser(userNickName);
    }
}
