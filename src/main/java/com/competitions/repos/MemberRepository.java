package com.competitions.repos;

import com.competitions.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query("select m from member m where m.captain.idPerson = :captain_id")
    List<Member> findByCaptainId(@Param("captain_id") Integer captainId);
}
