package com.competitions.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "authority")
@Getter
@Setter
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

//    @OneToMany(mappedBy = "authority")
//    private Set<Person> persons;

    @Builder
    public Authority(UserRoleEnum role) {
        this.role = role;
    }
}
