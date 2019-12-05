package com.competitions.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "authorities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @Builder
    public Authorities(UserRoleEnum role) {
        this.role = role;
    }
}
