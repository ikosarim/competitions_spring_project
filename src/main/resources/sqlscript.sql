create table hibernate_sequence
(
    next_val bigint null
);

create table person
(
    id_person        int          not null  primary key,
    person_name      varchar(255) null,
    person_nick_name varchar(255) null,
    person_surname   varchar(255) null
);

create table captain
(
    captain_experience double       null,
    captain_team_name  varchar(255) null,
    id_captain         int          not null    primary key,
    constraint FKe3usm0syrf46c1pfoloykp71q      foreign key (id_captain) references person (id_person)
);

create table competition_lead
(
    id_lead             int          not null   primary key,
    lead_certificates   varchar(255) null,
    lead_experience     double       null,
    lead_specialization varchar(255) null,
    constraint FKt8q0xkg4htwue2tp8lwwxshtm      foreign key (id_lead) references person (id_person)
);

create table competition
(
    id_competition          bigint       not null   primary key,
    competition_description varchar(255) null,
    competition_name        varchar(255) null,
    competition_reward      varchar(255) null,
    competition_lead_id     int          not null,
    constraint FK94ias3jfys4os9dvddu1kp1wm      foreign key (competition_lead_id) references competition_lead (id_lead)
);

create table competition_book
(
    id_captain     int    not null,
    id_competition bigint not null,
    primary key (id_captain, id_competition),
    constraint FKdhrttmpi112cmep9cfs8rpn0b  foreign key (id_captain) references captain (id_captain),
    constraint FKoutkurid0h6e59vhf6ki76djv  foreign key (id_competition) references competition (id_competition)
);

create table member
(
    id_member     int          not null     primary key,
    member_degree varchar(255) null,
    captain_id    int          null,
    constraint FK6jphk7wlvm8dd6xp8q0pum08t  foreign key (id_member) references person (id_person),
    constraint FKdj7nk9w1sf9vuemitls0169bp  foreign key (captain_id) references captain (id_captain)
);

create table passport
(
    id_passport     int         not null    primary key,
    date_of_issue   datetime(6) null,
    passport_number bigint      null,
    passport_series int         null,
    person_id       int         null,
    constraint FK6g8x932c2yca2nmyvmfh6i9g7  foreign key (person_id) references person (id_person)
);

create table phone
(
    id_phone  int          not null     primary key,
    phone_num varchar(255) null,
    person_id int          not null,
    constraint FKkk6uij3j6wikpnqlj9dymobs9  foreign key (person_id) references person (id_person)
);

create table request_for_enter
(
    id_request          bigint       not null   primary key,
    request_description varchar(255) null,
    request_status      bit          null,
    captain_id          int          null,
    member_id           int          null,
    constraint FKhiybwd27dlioybvwr0oiifcka   foreign key (captain_id) references captain (id_captain),
    constraint FKn5uaj9e1x02n15k2srkwqx32l   foreign key (member_id) references member (id_member)
);