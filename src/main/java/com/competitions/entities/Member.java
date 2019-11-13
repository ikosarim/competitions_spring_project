package com.competitions.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;

@Entity(name = "member")
@PrimaryKeyJoinColumn(name = "id_member", referencedColumnName = "id_person")
@NoArgsConstructor
@Getter
@Setter
public class Member extends Person implements Serializable {

    @Column(name = "member_degree", nullable = false)
    private String memberDegree;

    @ManyToOne
    @JoinColumn(name = "captain_id")
    private Captain captain;

    @OneToMany(mappedBy = "member", cascade = {PERSIST, MERGE, REFRESH}, fetch = EAGER, orphanRemoval = true)
    private Set<RequestForEnter> requestsForEnter;

    @Builder
    public Member(String personName, String personSurname, String personNickName, String password, Authorities authorities,
                  Passport passport, String memberDegree, Phone... phones) {
        super(personName, personSurname, personNickName, password, authorities, passport, phones);
        this.memberDegree = memberDegree;
    }

    @PreRemove
    public void removeRequests() {
        this.getRequestsForEnter().removeAll(getRequestsForEnter());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        if (!super.equals(o)) return false;
        Member member = (Member) o;
        return getMemberDegree().equals(member.getMemberDegree());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMemberDegree());
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberDegree='" + memberDegree + '\'' +
                ", captain=" + captain +
                '}';
    }
}
