package com.competitions.widgets;

import com.competitions.entities.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationWidget {

    private UserRoleEnum role;
    private String memberDegree = null;
    private String captainTeamName = null;
    private Double captainExperience = null;
    private Double leadExperience = null;
    private String leadCertificate = null;
    private String leadSpecialization = null;
    private String personName;
    private String personSurname;
    private String personNickName;
    private Integer passportSeries;
    private Integer passportNumber;
    private Integer dayOfIssue;
    private Integer monthOfIssue;
    private Integer yearOfIssue;
    private List<String> phoneNums;
}
