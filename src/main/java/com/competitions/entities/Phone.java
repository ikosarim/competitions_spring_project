package com.competitions.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "phone")
@NoArgsConstructor
@Getter
@Setter
public class Phone implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id_phone", nullable = false)
    private int idPhone;

    @Column(name = "phone_num", nullable = false)
    private String phoneNum;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Builder
    public Phone(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        Phone phone = (Phone) o;
        return getPhoneNum().equals(phone.getPhoneNum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoneNum());
    }

    @Override
    public String toString() {
        return "Phone{" +
                "idPhone=" + idPhone +
                ", phoneNum='" + phoneNum + '\'' +
                ", person=" + person +
                '}';
    }
}
