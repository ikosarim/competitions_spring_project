package com.competitions.services;

import com.competitions.entities.*;
import com.competitions.repos.CaptainRepository;
import com.competitions.repos.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.competitions.util.UtilFormatterClass.convertToYyyyMmDd;
import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service("memberService")
@Transactional
public class MemberServiceImpl implements MemberService<Member> {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CaptainRepository captainRepository;

    @Transactional(readOnly = true)
    List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> getAllMembersFromOwnTeam(Member member) {
        return memberRepository.findByCaptainId(member.getCaptain().getIdPerson());
    }

    @Override
    @Transactional(readOnly = true)
    public Member getMemberFromOwnTeamById(Member member, Integer memberId) {
        return getAllMembersFromOwnTeam(member).stream()
                .filter(m -> memberId.equals(m.getIdPerson()))
                .findAny()
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Member getByNickName(String nickName) {
        return findAll().stream()
                .filter(m -> nickName.equals(m.getPersonNickName()))
                .findAny()
                .orElse(null);
    }

    @Override
    public Member addNewPhone(Member member, String phoneNum) {
        Phone phone = Phone.builder()
                .phoneNum(phoneNum)
                .build();
        member.getPhone().add(phone);
        phone.setPerson(member);
        return memberRepository.save(member);
    }

    @Override
    public Member changePhone(Member member, String phoneNum, String newPhoneNum) {
        if (checkAndRemovePhone(member, phoneNum)) return member;
        return addNewPhone(member, newPhoneNum);
    }

    @Override
    public Member deleteAllPhones(Member member) {
        member.getPhone().removeAll(member.getPhone());
        return memberRepository.save(member);
    }

    @Override
    public Member deletePhone(Member member, String phoneNum) {
        if (checkAndRemovePhone(member, phoneNum)) return member;
        return memberRepository.save(member);
    }

    private boolean checkAndRemovePhone(Member member, String phoneNum2) {
        Phone phone = member.getPhone()
                .stream()
                .filter(p -> phoneNum2.equals(p.getPhoneNum()))
                .findAny()
                .orElse(null);
        if (phone == null) {
            return true;
        }
        member.getPhone().remove(phone);
        return false;
    }

    @Override
    public Member changeNickName(Member member, String newNickName) {
        member.setPersonNickName(newNickName);
        return memberRepository.save(member);
    }

    @Override
    public Member changeMemberDegree(Member member, String memberDegree) {
        member.setMemberDegree(memberDegree);
        return memberRepository.save(member);
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
    @Transactional(readOnly = true)
    public Set<Competition> getAllCompetitionsForUser(Member member) {
        if (member.getCaptain() == null) {
            return emptySet();
        }
        return memberRepository.findAllCompetitionsForUser(member.getCaptain().getPersonNickName());
    }

    @Override
    public Member createNewPerson(Map<String, Object> specialPersonData,
                                  String memberName, String memberSurname, String memberNickName,
                                  int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                                  String... phoneNumbers) throws IllegalArgumentException {
        String date = convertToYyyyMmDd(yearOfDate, monthOfDate, dayOfDate);
        Passport passport = Passport.builder()
                .passportSeries(passportSeries)
                .passportNumber(passportNumber)
                .dateOfIssue(date)
                .build();
        Set<Phone> phones = Stream.of(phoneNumbers).map(Phone::new).collect(toSet());
        Member member = Member.builder()
                .memberDegree(specialPersonData.get("memberDegree").toString())
                .personName(memberName)
                .personSurname(memberSurname)
                .personNickName(memberNickName)
                .passport(passport)
                .phones(Arrays.copyOf(phones.toArray(), phones.size(), Phone[].class))
                .build();
        passport.setPerson(member);
        phones.forEach(p -> p.setPerson(member));
        return memberRepository.save(member);
    }

    @Override
    public void removePerson(Member member) {
        memberRepository.delete(member);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<RequestForEnter> getAllRequestsForEnterOfMember(Member member) {
        return member.getRequestsForEnter();
    }

    @Override
    public Member createRequestForEnterInTeam(Captain captain, Member member, String description) {
        if (member.getCaptain() != null) {
            System.out.println("У мембера уже есть команда");
            return member;
        }
        RequestForEnter requestForEnter = RequestForEnter.builder()
                .requestForEnterPK(
                        RequestForEnterPK.builder()
                                .captainId(captain.getIdPerson())
                                .memberId(member.getIdPerson())
                                .build()
                )
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
    @Transactional(propagation = REQUIRES_NEW)
    public Member cancelRequestForEnter(Captain captain, Member member) {
        RequestForEnter requestForEnter = member.getRequestsForEnter()
                .stream()
                .filter(rfe -> captain.equals(rfe.getCaptain()))
                .findAny()
                .orElse(null);
        if (requestForEnter == null) {
            return member;
        }
        member.getRequestsForEnter().remove(requestForEnter);
        captain.getRequestsForEnter().remove(requestForEnter);
        memberRepository.save(member);
        captainRepository.save(captain);
        return member;
    }

    @Override
    public Set<Competition> getAllCompetitions() {
        return memberRepository.getAllCompetitions();
    }

    @Override
    public Competition getCompetitionByName(String competitionName) {
        return memberRepository.getCompetitionByName(competitionName);
    }

    @Override
    public Set<Captain> getAllTeamsForCompetition(String competitionName) {
        return memberRepository.getAllTeamsForCompetition(competitionName);
    }

    @Override
    public Set<Competition> findAllCompetitionsForUser(String userNickName) {
        return memberRepository.findAllCompetitionsForUser(userNickName);
    }
}
