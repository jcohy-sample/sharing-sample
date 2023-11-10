create table if not exists `person`
(
    id         integer AUTO_INCREMENT PRIMARY KEY,
    `name`     varchar(225),
    `age`      integer,
    `province` varchar(225)
);

create table if not exists `person_province_0`
(
    id         integer AUTO_INCREMENT PRIMARY KEY,
    `province` varchar(11),
    `user_id`  varchar(225),
    `record`   varchar(225),
    `remark`   varchar(255)
);


create table if not exists `person_province_1`
(
    id         integer AUTO_INCREMENT PRIMARY KEY,
    `province` varchar(11),
    `user_id`  varchar(225),
    `record`   varchar(225),
    `remark`   varchar(255)
);

create table if not exists `person_province_2`
(
    id         integer AUTO_INCREMENT PRIMARY KEY,
    `province` varchar(11),
    `user_id`  varchar(225),
    `record`   varchar(225),
    `remark`   varchar(255)
);


create table if not exists `person_province_3`
(
    id         integer AUTO_INCREMENT PRIMARY KEY,
    `province` varchar(11),
    `user_id`  varchar(225),
    `record`   varchar(225),
    `remark`   varchar(255)
);

create table if not exists `person_province_4`
(
    id         integer AUTO_INCREMENT PRIMARY KEY,
    `province` varchar(11),
    `user_id`  varchar(225),
    `record`   varchar(225),
    `remark`   varchar(255)
);

create table if not exists `person_province_5`
(
    id         integer AUTO_INCREMENT PRIMARY KEY,
    `province` varchar(11),
    `user_id`  varchar(225),
    `record`   varchar(225),
    `remark`   varchar(255)
);