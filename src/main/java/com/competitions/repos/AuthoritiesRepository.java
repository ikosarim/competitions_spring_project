package com.competitions.repos;

import com.competitions.entities.Authorities;
import com.competitions.entities.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

    @Query("from authorities a where a.role = :role")
    Authorities findByRoleName(@Param("role") UserRoleEnum role);
}
