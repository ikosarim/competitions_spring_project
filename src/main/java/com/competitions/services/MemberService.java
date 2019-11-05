package com.competitions.services;

import com.competitions.entities.Captain;
import com.competitions.entities.Member;
import com.competitions.entities.RequestForEnter;

import java.util.List;
import java.util.Set;

public interface MemberService {

    List<Member> findAllMembersFromOwnTeam(Member member); // Member, Captain

    Member findMemberFromOwnTeamById(Member member, Integer memberId); // Member, Captain

    Member changeMemberDegree(Member member, String memberDegree); // Member

    Member createRequestForEnterInTeam(Captain captain, Member member, String description); // Member

    Set<RequestForEnter> findAllRequestsForEnterOfMember(Member member); // Member

    Member cancelRequestForEnter(Captain captain, Member member); // Member

    Member leaveTeam(Member member); // Member

    Member createNewPerson(String memberDegree,
                      String personName, String personSurname, String personNickName,
                      int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                      String... phoneNumbers) throws IllegalArgumentException; // Guest

    void removePerson(Member member); // Member
}
