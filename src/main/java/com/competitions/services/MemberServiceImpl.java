package com.competitions.services;

import com.competitions.entities.*;
import com.competitions.repos.CaptainRepository;
import com.competitions.repos.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.competitions.util.UtilFormatterClass.convertToYyyyMmDd;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CaptainRepository captainRepository;

    @Override
    public Member findMemberById(Integer id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> findAllMembersFromOwnTeam(Member member) {
        if (member.getCaptain() == null) {
            return new ArrayList<>();
        }
        return memberRepository.findAll()
                .stream()
                .filter(m -> member.getCaptain().equals(m.getCaptain()))
                .collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Member findMemberFromOwnTeamById(Member member, Integer memberId) {
        for (Member mem : findAllMembersFromOwnTeam(member)) {
            if (memberId.equals(mem.getIdPerson())) return mem;
        }
        return null;
    }

    @Override
    public Member changeMemberDegree(Member member, String memberDegree) {
        member.setMemberDegree(memberDegree);
        return memberRepository.save(member);
    }

    @Override
    public Member createRequestForEnterInTeam(Captain captain, Member member, String description) {
        RequestForEnter requestForEnter = RequestForEnter.builder()
                .captain(captain)
                .member(member)
                .requestDescription(description)
                .build();
        member.getRequestsForEnter().add(requestForEnter);
        captain.getRequestsForEnter().add(requestForEnter);
        captainRepository.save(captain);
        return member;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<RequestForEnter> findAllRequestsForEnterOfMember(Member member) {
        return member.getRequestsForEnter();
    }

    @Override
    public Member cancelRequestForEnter(Integer requestId, Member member) {
        RequestForEnter requestForEnter = member.getRequestsForEnter()
                .stream()
                .filter(rfe -> requestId.equals(rfe.getIdRequest()))
                .findAny()
                .orElse(null);
        if (requestForEnter == null) {
            return member;
        }
        member.getRequestsForEnter().remove(requestForEnter);
        Captain captain = requestForEnter.getCaptain();
        captain.getRequestsForEnter().remove(requestForEnter);
        memberRepository.save(member);
        captainRepository.save(captain);
        return member;
    }

    @Override
    public Member leaveTeam(Member member) {
        Captain captain = member.getCaptain();
        captain.getMembers().remove(captain);
        member.setCaptain(null);
        captainRepository.save(captain);
        return member;
    }

    @Override
    public Member createNewPerson(String memberDegree,
                                  String personName, String personSurname, String personNickName,
                                  int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                                  String... phoneNumbers) throws IllegalArgumentException {
        String date = convertToYyyyMmDd(yearOfDate, monthOfDate, dayOfDate);
        Passport passport = createPassport(passportSeries, passportNumber, date);
        Set<Phone> phones = Stream.of(phoneNumbers).map(Phone::new).collect(toSet());
        Member member = createMember(memberDegree, personName, personSurname, personNickName, passport, phones);
        passport.setPerson(member);
        phones.forEach(p -> p.setPerson(member));
        return memberRepository.save(member);
    }

    private Member createMember(String memberDegree,
                                String personName, String personSurname, String personNickName,
                                Passport passport,
                                Set<Phone> phones) {
        return Member.builder()
                .memberDegree(memberDegree)
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
    public void removePerson(Member member) {
        memberRepository.delete(member);
    }
}
