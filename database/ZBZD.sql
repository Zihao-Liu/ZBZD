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
    primary key(pk_id)
);
insert into tag values(0,'20180405000000','20180405000000','根话题');

create table tag_relation(
	pk_id bigint unsigned auto_increment,
    gmt_create datetime not null,
    gmt_modified datetime not null,
    child bigint unsigned,
    father bigint unsigned,
    primary key (pk_id),
    foreign key (child) references tag(pk_id),
    foreign key (father) references tag(pk_id)
);

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
)
