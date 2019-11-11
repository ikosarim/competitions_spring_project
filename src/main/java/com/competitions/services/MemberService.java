package com.competitions.services;

import com.competitions.entities.Captain;
import com.competitions.entities.Member;
import com.competitions.entities.RequestForEnter;
import com.competitions.entities.UserRoleEnum;

import java.util.List;
import java.util.Set;

public interface MemberService {

    Member findMemberById(Integer id);

    List<Member> findAllMembersFromOwnTeam(Member member); // +Controller -(Member, Captain)

    Member findMemberFromOwnTeamById(Member member, Integer memberId); // +Controller -(Member, Captain)

    Member changeMemberDegree(Member member, String memberDegree); // +Controller -Member

    Member createRequestForEnterInTeam(Captain captain, Member member, String description); // +Controller -Member

    Set<RequestForEnter> findAllRequestsForEnterOfMember(Member member); // +Controller -Member

    Member cancelRequestForEnter(Integer requestId, Member member); // +Controller -Member

    Member leaveTeam(Member member); // +Controller -Member

    Member createNewPerson(String login, String password, UserRoleEnum role,
                           String memberDegree,
                           String personName, String personSurname, String personNickName,
                           int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                           String... phoneNumbers) throws IllegalArgumentException; // +Controller -Guest

    void removePerson(Member member); // +Controller -Member
}
