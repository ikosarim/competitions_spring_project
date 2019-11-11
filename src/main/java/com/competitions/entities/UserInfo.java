package com.competitions.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "user_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo {
    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private Integer idUser;
    @Column(name = "login", nullable = false, unique = true)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
}
