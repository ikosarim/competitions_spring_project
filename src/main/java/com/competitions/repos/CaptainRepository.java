package com.competitions.repos;

import com.competitions.entities.Captain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptainRepository extends PersonsRepository, JpaRepository<Captain, Integer> {
}
