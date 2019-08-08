create database if not exists mydms;
create table authorities
(
  username  varchar(50) null,
  authority varchar(50) null,
  constraint authorities_users_username_fk
    foreign key (username) references demodb.users (username)
);

INSERT INTO mydms.authorities (username, authority) VALUES ('test', 'ROLE_WEB');
create table documents
(
  id     int auto_increment
    primary key,
  path   varchar(128) null,
  type   varchar(3)   null,
  sender int          null,
  title  varchar(64)  null,
  date   date         null,
  text   varchar(255) null,
  user   varchar(50)  null,
  number varchar(64)  null,
  file   varchar(64)  null,
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

INSERT INTO mydms.documents (id, path, type, sender, title, date, text, user, number, file) VALUES (2, '/test/test/2019/01', 'R', 1, 'Auszug', '2019-08-08', 'BLABLA', 'test', 'R-214739', 'R-1223761.pdf');
create table documents_tag
(
  tag      varchar(50) not null,
  document int         not null,
  constraint documents_tag_documents_id_fk
    foreign key (document) references documents (id),
  constraint documents_tag_tag_tag_fk
    foreign key (tag) references tag (tag)
);

INSERT INTO mydms.documents_tag (tag, document) VALUES ('Finance', 2);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('OTHER', 2);
create table sender
(
  id   int auto_increment
    primary key,
  name varchar(32)  null,
  text varchar(128) null,
  date date         null,
  user varchar(50)  null,
  constraint sender_users_username_fk
    foreign key (user) references demodb.users (username)
);

INSERT INTO mydms.sender (id, name, text, date, user) VALUES (1, 'BankXYyy', 'The Bank XY from ZY', '2019-08-07', 'test');
create table tag
(
  tag   varchar(64)  not null,
  color varchar(128) null,
  user  varchar(50)  null,
  text  varchar(128) null,
  date  date         null,
  constraint tag_tag_uindex
    unique (tag),
  constraint tag_users_username_fk
    foreign key (user) references demodb.users (username)
);

alter table tag
  add primary key (tag);

INSERT INTO mydms.tag (tag, color, user, text, date) VALUES ('Finance', 'badge-primary', 'test', 'Finance Stuffafasfd', '2019-08-06');
INSERT INTO mydms.tag (tag, color, user, text, date) VALUES ('OTHER', 'badge-danger', 'test', 'TestText', '2019-08-05');
create table types
(
  short_name varchar(3)   not null,
  name       varchar(32)  null,
  text       varchar(255) null,
  user       varchar(50)  null,
  date       date         null,
  constraint types_short_uindex
    unique (short_name),
  constraint types_users_username_fk
    foreign key (user) references demodb.users (username)
);

alter table types
  add primary key (short_name);

INSERT INTO mydms.types (short_name, name, text, user, date) VALUES ('R', 'Rechnung', 'asdjfljasofd', 'test', '2019-08-08');
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

INSERT INTO mydms.users (username, password, enabled, name, date, email) VALUES ('asdnfadnf', 'aosjdfljf', 1, 'gdfgsdfgdfdfggsdfg', '2222-02-05', 'asdf@nafsd.de');
INSERT INTO mydms.users (username, password, enabled, name, date, email) VALUES ('baslkdfja', 'sdfasdf', 1, 'asdfasdfa', '1555-12-18', 'sadf@lsdjflk.com');
INSERT INTO mydms.users (username, password, enabled, name, date, email) VALUES ('bla', 'blapssw', 1, 'bla name', '1110-01-09', 'bla@mail.com');
INSERT INTO mydms.users (username, password, enabled, name, date, email) VALUES ('test', '{noop}test123', 1, 'asdfsdf', '2019-08-07', 'sfd@dsjh.com');
INSERT INTO mydms.users (username, password, enabled, name, date, email) VALUES ('testuser', '{bcrypt}$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.', 1, 'test name', '2018-10-19', 'test@mail.com');