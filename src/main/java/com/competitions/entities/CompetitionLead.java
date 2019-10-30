package com.competitions.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity(name = "competition_lead")
@PrimaryKeyJoinColumn(name = "id_lead", referencedColumnName = "id_person")
@NoArgsConstructor
@Getter
@Setter
public class CompetitionLead extends Person implements Serializable {

    @Column(name = "lead_experience", nullable = false)
    private double leadExperience;

    @Column(name = "lead_certificates", nullable = false)
    private String leadCertificates;

    @Column(name = "lead_specialization", nullable = false)
    private String leadSpecialization;

    @OneToMany(mappedBy = "competitionLead", cascade = ALL, fetch = EAGER, orphanRemoval = true)
    private Set<Competition> competitions;

    @Builder
    public CompetitionLead(String personName, String personSurname, String personNickName, Passport passport, double leadExperience,
                           String leadCertificates, String leadSpecialization, Phone... phones) {
        super(personName, personSurname, personNickName, passport, phones);
        this.leadExperience = leadExperience;
        this.leadCertificates = leadCertificates;
        this.leadSpecialization = leadSpecialization;
        this.competitions = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompetitionLead)) return false;
        if (!super.equals(o)) return false;
        CompetitionLead lead = (CompetitionLead) o;
        return Double.compare(lead.getLeadExperience(), getLeadExperience()) == 0 &&
                getLeadCertificates().equals(lead.getLeadCertificates()) &&
                getLeadSpecialization().equals(lead.getLeadSpecialization());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLeadExperience(), getLeadCertificates(), getLeadSpecialization());
    }

    @Override
    public String toString() {
        return "CompetitionLead{" +
                "leadExperience=" + leadExperience +
                ", leadCertificates='" + leadCertificates + '\'' +
                ", leadSpecialization='" + leadSpecialization + '\'' +
                ", competitions=" + competitions +
                '}';
    }
}
