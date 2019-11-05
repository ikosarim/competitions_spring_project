package com.competitions.services;

import com.competitions.entities.Person;

public interface PersonService {

    Integer getByNickName(String nickName); // Guest

    Person changeNickName(Person person, String newNickName); // Authorized

    Person addNewPhone(Person person, String phoneNum); // Authorized

    Person changePhone(Person person, String phoneNum, String newPhoneNum); // Authorized

    Person deleteAllPhones(Person person); // Authorized

    Person deletePhone(Person person, String phoneNum); // Authorized
}
