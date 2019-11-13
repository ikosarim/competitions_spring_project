package com.competitions.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;

@Entity(name = "captain")
@PrimaryKeyJoinColumn(name = "id_captain", referencedColumnName = "id_person")
@NoArgsConstructor
@Getter
@Setter
public class Captain extends Person implements Serializable {

    @Column(name = "captain_team_name", nullable = false)
    private String captainTeamName;

    @Column(name = "captain_experience", nullable = false)
    private double captainExperience;

    @OneToMany(mappedBy = "captain", cascade = {MERGE, REFRESH}, fetch = EAGER)
    private Set<Member> members;

    @OneToMany(mappedBy = "captain", cascade = {PERSIST, MERGE, REFRESH}, fetch = EAGER, orphanRemoval = true)
    private Set<RequestForEnter> requestsForEnter;

    @ManyToMany(mappedBy = "captainSet")
    private Set<Competition> competitionSet;

    @Builder
    public Captain(String personName, String personSurname, String personNickName, String password, Authorities authorities,
                   Passport passport, String captainTeamName, double captainExperience, Phone... phones) {
        super(personName, personSurname, personNickName, password, authorities, passport, phones);
        this.captainTeamName = captainTeamName;
        this.captainExperience = captainExperience;
        this.members = new HashSet<>();
        this.competitionSet = new HashSet<>();
    }

    @PreRemove
    private void removeCaptainFromCompetitions() {
        for (Competition competition : competitionSet) {
            competition.removeCaptain(this);
        }
        this.getRequestsForEnter().removeAll(getRequestsForEnter());
    }

    public void removeCompetition(Competition competition) {
        competitionSet.remove(competition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Captain)) return false;
        if (!super.equals(o)) return false;
        Captain captain = (Captain) o;
        return Double.compare(captain.getCaptainExperience(), getCaptainExperience()) == 0 &&
                getCaptainTeamName().equals(captain.getCaptainTeamName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCaptainTeamName(), getCaptainExperience());
    }

    @Override
    public String toString() {
        return "Captain{" +
                "captainTeamName='" + captainTeamName + '\'' +
                ", captainExperience=" + captainExperience +
                ", members=" + members +
                ", requestsForEnter=" + requestsForEnter +
                ", competitionSet=" + competitionSet +
                '}';
    }
}
