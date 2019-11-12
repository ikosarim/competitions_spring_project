package com.competitions.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.Arrays.asList;
import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.InheritanceType.JOINED;

@Entity(name = "person")
@NoArgsConstructor
@Inheritance(strategy = JOINED)
@Getter
@Setter
public abstract class Person {

    @Id
    @GeneratedValue
    @Column(name = "id_person", nullable = false)
    private Integer idPerson;

    @Column(name = "person_name", nullable = false)
    private String personName;

    @Column(name = "person_surname", nullable = false)
    private String personSurname;

    @Column(name = "person_nick_name", nullable = false, unique = true)
    private String personNickName;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "person", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Set<Phone> phone;

    @OneToOne(mappedBy = "person", cascade = {PERSIST, REMOVE}, fetch = LAZY, orphanRemoval = true)
    private Passport passport;

    @ManyToOne
    @JoinColumn(name = "authority_id", nullable = false)
    private Authority authority;

    public Person(String personName, String personSurname, String personNickName, String password, Authority authority,
                  Passport passport, Phone... phones) {
        this.personName = personName;
        this.personSurname = personSurname;
        this.personNickName = personNickName;
        this.password = password;
        this.authority = authority;
        this.passport = passport;
        this.phone = new HashSet<>();
        phone.addAll(asList(phones));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getPersonName().equals(person.getPersonName()) &&
                getPersonSurname().equals(person.getPersonSurname()) &&
                getPersonNickName().equals(person.getPersonNickName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonName(), getPersonSurname(), getPersonNickName());
    }

    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idPerson +
                ", personName='" + personName + '\'' +
                ", personSurname='" + personSurname + '\'' +
                ", personNickName='" + personNickName + '\'' +
                ", phone=" + phone +
                ", passport=" + passport +
                '}';
    }
}