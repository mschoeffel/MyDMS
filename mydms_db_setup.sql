create database if not exists mydms
create table authorities
(
  username  varchar(50) null,
  authority varchar(50) null,
  constraint authorities_users_username_fk
    foreign key (username) references demodb.users (username)
);

INSERT INTO mydms.authorities (username, authority) VALUES ('test', 'ROLE_WEB');
create table users
(
  username varchar(50)          not null
    primary key,
  password varchar(255)         null,
  enabled  tinyint(1) default 1 null
);

INSERT INTO mydms.users (username, password, enabled) VALUES ('test', '{noop}test123', 1);