package com.competitions.repos;

import com.competitions.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer>, UserRepository {
}
