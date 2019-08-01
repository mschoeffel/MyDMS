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
  enabled  tinyint(1) default 1 null,
  name     varchar(64)          null,
  date     date                 null,
  email    varchar(255)         null
);

INSERT INTO mydms.users (username, password, enabled, name, date, email) VALUES ('baslkdfja', 'sdfasdf', 1, 'asdfasdfa', '1555-12-18', 'sadf@lsdjflk.com');
INSERT INTO mydms.users (username, password, enabled, name, date, email) VALUES ('bla', 'blapssw', 1, 'bla name', '1110-01-09', 'bla@mail.com');
INSERT INTO mydms.users (username, password, enabled, name, date, email) VALUES ('test', '{noop}test123', 1, 'test nnanana', '2019-07-29', 'asdfasdf@dalfskj.com');
INSERT INTO mydms.users (username, password, enabled, name, date, email) VALUES ('testuser', 'testpssw', 1, 'test name', '2018-10-19', 'test@mail.com');