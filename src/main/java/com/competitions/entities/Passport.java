package com.competitions.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "passport",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"passport_series", "passport_number"})})
@NoArgsConstructor
@Getter
@Setter
public class Passport implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_passport", nullable = false)
    private int idPassport;

    @Column(name = "passport_series", nullable = false)
    private int passportSeries;

    @Column(name = "passport_number", nullable = false)
    private int passportNumber;

    @Column(name = "date_of_issue", nullable = false)
    private String dateOfIssue;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Builder
    public Passport(int passportSeries, int passportNumber, String dateOfIssue) {
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.dateOfIssue = dateOfIssue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passport)) return false;
        Passport passport = (Passport) o;
        return getPassportSeries() == passport.getPassportSeries() &&
                getPassportNumber() == passport.getPassportNumber() &&
                getDateOfIssue().equals(passport.getDateOfIssue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPassportSeries(), getPassportNumber(), getDateOfIssue());
    }

    @Override
    public String toString() {
        return "Passport{" +
                "idPassport=" + idPassport +
                ", passportSeries=" + passportSeries +
                ", passportNumber=" + passportNumber +
                ", dateOfIssue=" + dateOfIssue +
                ", person=" + person +
                '}';
    }
}
