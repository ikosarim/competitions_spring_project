package com.competitions.services;

import com.competitions.entities.Person;
import com.competitions.entities.Phone;
import com.competitions.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public Integer getByNickName(String nickName) {
        Optional<Person> person = personRepository.findAll()
                .stream()
                .filter(p -> nickName.equals(p.getPersonNickName()))
                .findAny();
        if (person.isEmpty()) {
            return null;
        }
        return person.get().getIdPerson();
    }

    @Override
    public Person changeNickName(Person person, String newNickName) {
        person.setPersonNickName(newNickName);
        return personRepository.save(person);
    }

    @Override
    public Person addNewPhone(Person person, String phoneNum) {
        Phone phone = Phone.builder()
                .phoneNum(phoneNum)
                .build();
        person.getPhone().add(phone);
        phone.setPerson(person);
        return personRepository.save(person);
    }

    @Override
    public Person changePhone(Person person, String phoneNum, String newPhoneNum) {
        if (checkAndRemovePhone(person, phoneNum)) return person;
        return addNewPhone(person, newPhoneNum);
    }

    @Override
    public Person deleteAllPhones(Person person) {
        person.getPhone().removeAll(person.getPhone());
        return personRepository.save(person);
    }

    @Override
    public Person deletePhone(Person person, String phoneNum) {
        if (checkAndRemovePhone(person, phoneNum)) return person;
        return personRepository.save(person);
    }

    private boolean checkAndRemovePhone(Person person, String phoneNum) {
        Phone phone = person.getPhone()
                .stream()
                .filter(p -> phoneNum.equals(p.getPhoneNum()))
                .findAny()
                .orElse(null);
        if (phone == null) {
            return true;
        }
        person.getPhone().remove(phone);
        return false;
    }
}
