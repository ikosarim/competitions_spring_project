package com.competitions.repos;

import com.competitions.entities.CompetitionLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<CompetitionLead, Integer> {
}
