CREATE DATABASE IF NOT EXISTS mydms
create table authorities
(
  username  varchar(50) null,
  authority varchar(50) null,
  constraint authorities_users_username_fk
    foreign key (username) references demodb.users (username)
);

INSERT INTO mydms.authorities (username, authority)
VALUES ('test', 'ROLE_WEB');
create table documents
(
  id     int auto_increment
    primary key,
  path   varchar(128) null,
  type   varchar(3) null,
  sender int null,
  title  varchar(64) null,
  date   date null,
  text   varchar(255) null,
  user   varchar(50) null,
  number varchar(64) null,
  file   varchar(64) null,
  constraint documents_number_uindex
    unique (number),
  constraint documents_sender_id_fk
    foreign key (sender) references sender (id),
  constraint documents_types_short_name_fk
    foreign key (type) references types (short_name)
      on update cascade on delete cascade,
  constraint documents_users_username_fk
    foreign key (user) references demodb.users (username)
);

create table documents_tag
(
  tag      varchar(50) not null,
  document int         not null,
  constraint documents_tag_documents_id_fk
    foreign key (document) references documents (id),
  constraint documents_tag_tag_tag_fk
    foreign key (tag) references tag (tag)
);

create table sender
(
  id   int auto_increment
    primary key,
  name varchar(32) null,
  text varchar(128) null,
  date date null,
  user varchar(50) null,
  constraint sender_users_username_fk
    foreign key (user) references demodb.users (username)
);

create table tag
(
  tag   varchar(64) not null,
  color varchar(128) null,
  user  varchar(50) null,
  text  varchar(128) null,
  date  date null,
  constraint tag_tag_uindex
    unique (tag),
  constraint tag_users_username_fk
    foreign key (user) references demodb.users (username)
);

alter table tag
  add primary key (tag);

create table types
(
  short_name varchar(3) not null,
  name       varchar(32) null,
  text       varchar(255) null,
  user       varchar(50) null,
  date       date null,
  constraint types_short_uindex
    unique (short_name),
  constraint types_users_username_fk
    foreign key (user) references demodb.users (username)
);

alter table types
  add primary key (short_name);

create table users
(
  username varchar(50) not null
    primary key,
  password varchar(255) null,
  enabled  tinyint(1) default 1 null,
  name     varchar(64) null,
  date     date null,
  email    varchar(255) null
);

INSERT INTO mydms.users (username, password, enabled, name, date, email)
VALUES ('test', '{noop}test123', 1, 'asdfsdf', '2019-08-07', 'sfd@dsjh.com');