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

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "competition")
@NoArgsConstructor
@Getter
@Setter
public class Competition implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY, generator = "competition_generator")
    @SequenceGenerator(name = "competition_generator")
    @Column(name = "id_competition", nullable = false)
    private int idCompetition;

    @Column(name = "competition_name", nullable = false, unique = true)
    private String competitionName;

    @Column(name = "competition_description", nullable = false)
    private String competitionDescription;

    @Column(name = "competition_reward", nullable = false)
    private String competitionReward;

    @ManyToOne
    @JoinColumn(name = "competition_lead_id", nullable = false)
    private CompetitionLead competitionLead;

    @ManyToMany
    @JoinTable(name = "competition_book",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "captain_id"))
    private Set<Captain> captainSet;

    @Builder
    public Competition(String competitionName, String competitionDescription, String competitionReward) {
        this.competitionName = competitionName;
        this.competitionDescription = competitionDescription;
        this.competitionReward = competitionReward;
        this.captainSet = new HashSet<>();
    }

    @PreRemove
    public void removeCompetitionFromCaptain() {
        for (Captain captain: captainSet) {
            captain.removeCompetition(this);
        }
    }

    void removeCaptain(Captain captain) {
        captainSet.remove(captain);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Competition)) return false;
        Competition that = (Competition) o;
        return getCompetitionName().equals(that.getCompetitionName()) &&
                getCompetitionDescription().equals(that.getCompetitionDescription()) &&
                getCompetitionReward().equals(that.getCompetitionReward());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompetitionName(), getCompetitionDescription(), getCompetitionReward());
    }

    @Override
    public String toString() {
        return "Competition{" +
                ", competitionName='" + competitionName + '\'' +
                ", competitionDescription='" + competitionDescription + '\'' +
                ", competitionReward='" + competitionReward + '\'' +
                ", competitionLead=" + competitionLead +
                ", captainSet=" + captainSet +
                '}';
    }
}
