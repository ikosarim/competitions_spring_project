package com.competitions.services;

import com.competitions.entities.Captain;
import com.competitions.entities.Competition;
import com.competitions.entities.Member;
import com.competitions.entities.UserRoleEnum;

import java.util.List;

public interface CaptainService {
    List<Captain> getAllCaptains(); // +Controller -(Member, Captain)

    Captain getCaptainById(Integer id); // +Controller -(Member, Captain)

    Captain changeTeamName(Captain captain, String newTeamName); // +Controller -Captain

    Captain changeExperience(Captain captain, double newExperience); // +Controller -Captain

    Captain showRequestToCaptain(Captain captain, Integer requestId); // +Controller -Captain

    Captain acceptMemberEnterToCaptain(Captain captain, Integer requestId); // +Controller -Captain

    Captain declineMemberEnterToCaptain(Captain captain, Integer requestId); // +Controller -Captain

    Captain deleteMemberFromCaptain(Captain captain, Member member); // +Controller -Captain

    Captain takePartInTheCompetition(Captain captain, Competition competition); // +Controller -Captain

    Captain leaveTheCompetition(Captain captain, Competition competition); // +Controller -Captain

    Captain createNewPerson(String login, String password, UserRoleEnum role,
                            String captainTeamName, double captainExperience,
                            String personName, String personSurname, String personNickName,
                            int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                            String... phoneNumbers) throws IllegalArgumentException; // +Controller -Captain

    void removePerson(Captain member); // +Controller -Captain
}
