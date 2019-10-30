package com.competitions.services;

import com.competitions.entities.*;

import java.util.List;
import java.util.Set;

public interface CaptainService<P extends Person> extends PersonService<P> {
    List<Captain> getAllCaptains();

    Captain getCaptainById(Integer id);

    Captain changeTeamName(Captain captain, String newTeamName);

    Captain changeExperience(Captain captain, double newExperience);

    Set<RequestForEnter> getAllRequestsToCaptain(Captain captain);

    Captain showRequestToCaptain(Captain captain, Member member);

    Captain acceptMemberEnterToCaptain(Captain captain, Member member);

    Captain declineMemberEnterToCaptain(Captain captain, Member member);

    Captain deleteMemberFromCaptain(Captain captain, Member member);

    Set<Competition> getAllCompetitionsForUser(Captain captain);

    Captain takePartInTheCompetition(Captain captain, Competition competition);

    Captain leaveTheCompetition(Captain captain, Competition competition);
}
