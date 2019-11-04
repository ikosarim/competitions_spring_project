package com.competitions.services;

import com.competitions.entities.Captain;
import com.competitions.entities.Competition;
import com.competitions.entities.Member;
import com.competitions.entities.Person;

import java.util.Map;
import java.util.Set;

public interface PersonService<P extends Person> {
    P getByNickName(String nickName);

    P changeNickName(P person, String newNickName);

    P addNewPhone(P captain, String phoneNum);

    P changePhone(P member, String phoneNum, String newPhoneNum);

    P deleteAllPhones(P member);

    P deletePhone(P member, String phoneNum);

    P createNewPerson(Map<String, Object> specialPersonData,
                      String personName, String personSurname, String personNickName,
                      int passportSeries, int passportNumber, int dayOfDate, int monthOfDate, int yearOfDate,
                      String... phoneNumbers) throws IllegalArgumentException;

    void removePerson(P member);

    Set<Competition> getAllCompetitions();

    Competition getCompetitionByName(String competitionName);

    Set<Captain> getAllTeamsForCompetition(String competitionName);

    Set<Competition> findAllCompetitionsForUser(String userNickName);

    // TODO: 04.11.2019 Переписать сервисы, используя паттерн фасад 
}
