package com.competitions.repos;

import com.competitions.entities.Authority;
import com.competitions.entities.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    @Query("from authority a where a.role = :role")
    Authority findByRoleName(@Param("role") UserRoleEnum role);
}
