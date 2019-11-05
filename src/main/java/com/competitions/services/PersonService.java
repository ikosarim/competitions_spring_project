package com.competitions.services;

import com.competitions.entities.Person;

public interface PersonService {

    Integer getByNickName(String nickName); // +Controller -Guest

    Person changeNickName(Person person, String newNickName); // +Controller -Authorized

    Person addNewPhone(Person person, String phoneNum); // +Controller Authorized

    Person changePhone(Person person, String phoneNum, String newPhoneNum); // +Controller Authorized

    Person deleteAllPhones(Person person); // +Controller Authorized

    Person deletePhone(Person person, String phoneNum); // +Controller Authorized
}
