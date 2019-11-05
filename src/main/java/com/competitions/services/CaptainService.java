package com.competitions.services;

import com.competitions.entities.Captain;
import com.competitions.entities.Competition;
import com.competitions.entities.Member;
import com.competitions.entities.RequestForEnter;

import java.util.List;
import java.util.Set;

public interface CaptainService {
    List<Captain> getAllCaptains(); // Member, Captain

    Captain getCaptainById(Integer id); // Member, Captain

    Captain changeTeamName(Captain captain, String newTeamName); // +Controller -Captain

    Captain changeExperience(Captain captain, double newExperience); // +Controller -Captain

    Set<RequestForEnter> findAllRequestsToCaptain(Captain captain); // Captain

    Captain showRequestToCaptain(Captain captain, Member member); // Captain

    Captain acceptMemberEnterToCaptain(Captain captain, Member member); // Captain

    Captain declineMemberEnterToCaptain(Captain captain, Member member); // Captain

    Captain deleteMemberFromCaptain(Captain captain, Member member); // Captain

    Captain takePartInTheCompetition(Captain captain, Competition competition); // Captain

    Captain leaveTheCompetition(Captain captain, Competition competition); // Captain

    Captain createNewPerson(String captainTeamName, double captainExperience,
                            String personName, String personSurname, String personNickName,
                            int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                            String... phoneNumbers) throws IllegalArgumentException; // +Controller -Captain

    void removePerson(Captain member); // +Controller -Captain
}
