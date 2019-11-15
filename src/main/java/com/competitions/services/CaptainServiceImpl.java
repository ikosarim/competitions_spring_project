package com.competitions.services;

import com.competitions.entities.*;
import com.competitions.repos.AuthoritiesRepository;
import com.competitions.repos.CaptainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.competitions.util.UtilFormatterClass.convertToYyyyMmDd;
import static java.util.stream.Collectors.toSet;

@Service
@Transactional
public class CaptainServiceImpl implements CaptainService {

    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    CaptainRepository captainRepository;
    @Resource(name = "myPasswordEncoder")
    PasswordEncoder encoder;

    @Override
    @Transactional(readOnly = true)
    public List<Captain> getAllCaptains() {
        return captainRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Captain getCaptainById(Integer id) {
        return captainRepository.findById(id).orElse(null);
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
    public Captain showRequestToCaptain(Captain captain, Integer requestId) {
        Optional<RequestForEnter> request = findNeedRequestForEnter(captain, requestId);
        if (request.isEmpty()) return null;
        request.get().setRequestStatus(true);
        return captainRepository.save(captain);
    }

    private Optional<RequestForEnter> findNeedRequestForEnter(Captain captain, Integer requestId) {
        return captain.getRequestsForEnter().stream()
                .filter(req -> requestId.equals(req.getIdRequest()))
                .findAny();
    }

    @Override
    public Captain acceptMemberEnterToCaptain(Captain captain, Integer requestId) {
        Optional<RequestForEnter> request = findNeedRequestForEnter(captain, requestId);
        if (request.isEmpty()) return null;
        captain.getRequestsForEnter().remove(request.get());
        Member member = request.get().getMember();
        member.getRequestsForEnter().removeAll(member.getRequestsForEnter());
        captain.getMembers().add(member);
        member.setCaptain(captain);
        return captainRepository.save(captain);
    }

    @Override
    public Captain declineMemberEnterToCaptain(Captain captain, Integer requestId) {
        Optional<RequestForEnter> request = findNeedRequestForEnter(captain, requestId);
        if (request.isEmpty()) return null;
        Member member = request.get().getMember();
        member.getRequestsForEnter().remove(request.get());
        captain.getRequestsForEnter().remove(request.get());
        return captainRepository.save(captain);
    }

    @Override
    public Captain deleteMemberFromCaptain(Captain captain, Member member) {
        captain.getMembers().remove(member);
        return captainRepository.save(captain);
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
    public Captain createNewPerson(String password, UserRoleEnum role,
                                   String captainTeamName, double captainExperience,
                                   String personName, String personSurname, String personNickName,
                                   int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                                   String... phoneNumbers) throws IllegalArgumentException {
        String date = convertToYyyyMmDd(yearOfDate, monthOfDate, dayOfDate);
        Passport passport = createPassport(passportSeries, passportNumber, date);
        Set<Phone> phones = Stream.of(phoneNumbers).map(Phone::new).collect(toSet());
        Captain captain = createCaptain(password, role, captainTeamName, captainExperience, personName, personSurname,
                personNickName, passport, phones);
        passport.setPerson(captain);
        phones.forEach(p -> p.setPerson(captain));
        return captainRepository.save(captain);
    }

    private Captain createCaptain(String password, UserRoleEnum role,
                                  String captainTeamName, double captainExperience,
                                  String personName, String personSurname, String personNickName,
                                  Passport passport,
                                  Set<Phone> phones) {
        return Captain.builder()
                .captainExperience(captainExperience)
                .captainTeamName(captainTeamName)
                .personName(personName)
                .personNickName(personNickName)
                .personSurname(personSurname)
                .password(encoder.encode(password))
                .authorities(authoritiesRepository.findByRoleName(role))
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
    public void removePerson(Captain captain) {
        captainRepository.delete(captain);
    }
}
