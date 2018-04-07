create table user(
	pk_id bigint unsigned auto_increment,
    gmt_create datetime not null,
    gmt_modified datetime not null,
    uk_username varchar(20) not null,
    password varchar(20) not null,
    nickname varchar(20) not null,
    privacy tinyint unsigned default 0,
    gender varchar(6) default 'male',
    introduction text,
    primary key(pk_id)
);

create table follow(
	pk_id bigint unsigned auto_increment,
    gmt_create datetime not null,
    gmt_modified datetime not null,
	follower_id bigint unsigned,
    following_id bigint unsigned,
	primary key(pk_id),
    foreign key(follower_id) references user(pk_id),
    foreign key(following_id) references user(pk_id)
);

create table question(
	pk_id bigint unsigned auto_increment,
    gmt_create datetime not null,
    gmt_modified datetime not null,
    title varchar(100) not null,
    content text not null,
    is_anonymous tinyint unsigned default 0,
    user_id bigint unsigned not null,
    primary key(pk_id),
    foreign key(user_id) references user(pk_id)
);

create table user_watch_question(
	pk_id bigint unsigned auto_increment,
    gmt_create datetime not null,
    gmt_modified datetime not null,
	user_id bigint unsigned,
    question_id bigint unsigned,
    primary key (pk_id),
    foreign key(user_id) references user(pk_id),
    foreign key(question_id) references question(pk_id)
);

create table tag(
	pk_id bigint unsigned auto_increment,
    gmt_create datetime not null,
    gmt_modified datetime not null,
    name varchar(10) unique,
    father_id bigint unsigned default 0,
    primary key(pk_id)
);
insert into tag values(0,'20180405000000','20180405000000','根话题',0);


create table question_belong_tag(
	pk_id bigint unsigned auto_increment,
    gmt_create datetime not null,
    gmt_modified datetime not null,
    tag_id bigint unsigned,
    question_id bigint unsigned,
    primary key (pk_id),
    foreign key (tag_id) references tag (pk_id),
    foreign key (question_id) references question (pk_id)
);

create table answer(
	pk_id bigint unsigned auto_increment,
    gmt_create datetime not null,
    gmt_modified datetime not null,
    content text not null,
    is_anonymous tinyint unsigned default 0,
    question_id bigint unsigned,
    user_id bigint unsigned,
    primary key (pk_id),
    foreign key (question_id) references question (pk_id),
    foreign key(user_id) references user(pk_id)
);

create table favourite(
	pk_id bigint unsigned auto_increment,
    gmt_create datetime not null,
    gmt_modified datetime not null,
    name varchar(10) not null,
    user_id bigint unsigned,
    primary key (pk_id),
    foreign key(user_id) references user(pk_id)
);

create table user_response_answer(
	pk_id bigint unsigned auto_increment,
    gmt_create datetime not null,
    gmt_modified datetime not null,
    is_like tinyint unsigned not null,
    user_id bigint unsigned,
    answer_id bigint unsigned,
    primary key(pk_id),
    foreign key(user_id) references user(pk_id),
    foreign key(answer_id) references answer(pk_id)
);

create table user_collect_answer(
	pk_id bigint unsigned auto_increment,
    gmt_create datetime not null,
    gmt_modified datetime not null,
    user_id bigint unsigned,
    answer_id bigint unsigned,
    favourite_id bigint unsigned,
    primary key(pk_id),
    foreign key(user_id) references user(pk_id),
    foreign key(answer_id) references answer(pk_id),
    foreign key(favourite_id) references favourite(pk_id)
);


INSERT INTO `zbzd`.`tag` (`pk_id`, `gmt_create`, `gmt_modified`, `name`, `father_id`) VALUES ('2', '2018-04-06 00:00:00', '2018-04-06 00:00:00', '游戏', '1');
INSERT INTO `zbzd`.`tag` (`pk_id`, `gmt_create`, `gmt_modified`, `name`, `father_id`) VALUES ('3', '2018-04-06 00:00:00', '2018-04-06 00:00:00', '运动', '1');
INSERT INTO `zbzd`.`tag` (`pk_id`, `gmt_create`, `gmt_modified`, `name`, `father_id`) VALUES ('4', '2018-04-06 00:00:00', '2018-04-06 00:00:00', '互联网', '1');
INSERT INTO `zbzd`.`tag` (`pk_id`, `gmt_create`, `gmt_modified`, `name`, `father_id`) VALUES ('5', '2018-04-06 00:00:00', '2018-04-06 00:00:00', '单机游戏', '2');
INSERT INTO `zbzd`.`tag` (`pk_id`, `gmt_create`, `gmt_modified`, `name`, `father_id`) VALUES ('6', '2018-04-06 00:00:00', '2018-04-06 00:00:00', '网络游戏', '2');
INSERT INTO `zbzd`.`tag` (`pk_id`, `gmt_create`, `gmt_modified`, `name`, `father_id`) VALUES ('7', '2018-04-06 00:00:00', '2018-04-06 00:00:00', '足球', '3');
