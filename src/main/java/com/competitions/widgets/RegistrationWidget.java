package com.competitions.widgets;

import com.competitions.entities.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationWidget {

    private String password;
    private UserRoleEnum role;
    private String memberDegree;
    private String captainTeamName;
    private Double captainExperience;
    private Double leadExperience;
    private String leadCertificate;
    private String leadSpecialization;
    private String personName;
    private String personSurname;
    private String personNickName;
    private Integer passportSeries;
    private Integer passportNumber;
    private Integer dayOfIssue;
    private Integer monthOfIssue;
    private Integer yearOfIssue;
    private List<String> phoneNums = new ArrayList<>();
}
