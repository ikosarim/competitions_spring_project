package com.competitions.repos;

import com.competitions.entities.Person;

public interface UserRepository {

    Person findByLogin(String nickName);
}
