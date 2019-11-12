package com.competitions.repos;

import com.competitions.entities.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Transactional
@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Person findByLogin(String nickName) {
        Query query = entityManager.createQuery("from person p where p.personNickName = :nickName");
        query.setParameter("nickName", nickName);
        return (Person) query.getSingleResult();
    }
}
