package com.competitions.services;

import com.competitions.entities.*;

import java.util.List;
import java.util.Set;

public interface MemberService<P extends Person> extends PersonService<P> {
    List<Member> getAllMembersFromOwnTeam(Member member);

    Member getMemberFromOwnTeamById(Member member, Integer memberId);

    Member changeMemberDegree(Member member, String memberDegree);

    Set<Competition> getAllCompetitionsForUser(Member member);

    Member createRequestForEnterInTeam(Captain captain, Member member, String description);

    Set<RequestForEnter> getAllRequestsForEnterOfMember(Member member);

    Member cancelRequestForEnter(Captain captain, Member member);

    Member leaveTeam(Member member);
}
