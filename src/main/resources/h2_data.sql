DROP TABLE IF EXISTS users;
create table users
(
  username varchar(50) not null,
  password varchar(255),
  enabled  tinyint(1) default 1,
  name     varchar(64),
  date     date,
  email    varchar(255),
  primary key(username)
);
INSERT INTO users (username, password, enabled, name, date, email) VALUES ('test', '{noop}test123', 1, 'test user', '2019-08-07', 'test@mail.com');

DROP TABLE IF EXISTS authorities;
create table authorities
(
  username  varchar(50),
  authority varchar(50),
  foreign key (username) references users (username)
);

INSERT INTO authorities (username, authority) VALUES ('test', 'ROLE_WEB');

DROP TABLE IF EXISTS types;
create table types
(
  short_name varchar(3) not null,
  name       varchar(32),
  text       varchar(255),
  user       varchar(50),
  date       date,
  primary key(short_name),
  foreign key (user) references users (username)
);

INSERT INTO types (short_name, name, text, user, date) VALUES ('B', 'Blanko Document', 'Everything that doesnt fit somewhere else', 'test', '2019-08-25');
INSERT INTO types (short_name, name, text, user, date) VALUES ('FB', 'Fuel Bill', 'Bills from purchasing fuel', 'test', '2019-08-25');
INSERT INTO types (short_name, name, text, user, date) VALUES ('I', 'Invoice', 'Invoices', 'test', '2019-08-25');
INSERT INTO types (short_name, name, text, user, date) VALUES ('L', 'Licence', 'Licences', 'test', '2019-08-25');
INSERT INTO types (short_name, name, text, user, date) VALUES ('M', 'Paycheck', 'Paychecks received', 'test', '2019-08-25');

DROP TABLE IF EXISTS tag;
create table tag
(
  tag   varchar(64) not null,
  color varchar(128),
  user  varchar(50),
  text  varchar(128),
  date  date,
  primary key(tag),
  foreign key (user) references users (username)
);

INSERT INTO tag (tag, color, user, text, date) VALUES ('Car', 'badge-secondary', 'test', 'Car related stuff', '2019-08-25');
INSERT INTO tag (tag, color, user, text, date) VALUES ('Education', 'badge-warning', 'test', 'Education realted stuff', '2019-08-25');
INSERT INTO tag (tag, color, user, text, date) VALUES ('Finance', 'badge-primary', 'test', 'Everything finance related', '2019-08-25');
INSERT INTO tag (tag, color, user, text, date) VALUES ('Form', 'badge-dark', 'test', 'Forms', '2019-08-25');
INSERT INTO tag (tag, color, user, text, date) VALUES ('High Prio', 'badge-danger', 'test', 'Important stuff', '2019-08-25');
INSERT INTO tag (tag, color, user, text, date) VALUES ('Income', 'badge-success', 'test', 'Stuff i got money from', '2019-08-25');
INSERT INTO tag (tag, color, user, text, date) VALUES ('Low Prio', 'badge-light', 'test', 'Stuff thats quite irrelevant', '2019-08-25');
INSERT INTO tag (tag, color, user, text, date) VALUES ('TODO', 'badge-info', 'test', 'Stuff that needs some work', '2019-08-25');

DROP TABLE IF EXISTS sender;
create table sender
(
  id int auto_increment,
  name varchar(32),
  text varchar(128),
  date date,
  user varchar(50),
  primary key(id),
  foreign key (user) references users (username)
);

INSERT INTO sender (id, name, text, date, user) VALUES (2, 'Bank', 'Private Bank', '2019-08-25', 'test');
INSERT INTO sender (id, name, text, date, user) VALUES (3, 'Car Service', 'Car Service of XY', '2019-08-25', 'test');
INSERT INTO sender (id, name, text, date, user) VALUES (4, 'Supermarket', 'Nearby Supermarket', '2019-08-25', 'test');
INSERT INTO sender (id, name, text, date, user) VALUES (5, 'Online Shop XY', 'Online Shop XY', '2019-08-25', 'test');
INSERT INTO sender (id, name, text, date, user) VALUES (6, 'Fuel Station', 'Fuel Station XY', '2019-08-25', 'test');
INSERT INTO sender (id, name, text, date, user) VALUES (7, 'School', 'High school stuff', '2019-08-25', 'test');
INSERT INTO sender (id, name, text, date, user) VALUES (8, 'Employer XY', 'Stuff from the company', '2019-08-25', 'test');
INSERT INTO sender (id, name, text, date, user) VALUES (9, 'College XY', 'College XY Related Stuff', '2019-08-25', 'test');
INSERT INTO sender (id, name, text, date, user) VALUES (10, 'Landlord XY', 'Landlord XY', '2019-08-25', 'test');

DROP TABLE IF EXISTS documents;
create table documents
(
  id int auto_increment,
  path      varchar(128),
  type      varchar(3)  ,
  sender    int         ,
  title     varchar(64) ,
  date      date        ,
  text      varchar(255),
  user      varchar(50) ,
  number    varchar(64) ,
  file      varchar(64) ,
  file_rand varchar(10) ,
    primary key (id),
    foreign key (sender) references sender (id),
    foreign key (type) references types (short_name)
      on update cascade on delete cascade,
    foreign key (user) references users (username)
);

INSERT INTO documents (id, path, type, sender, title, date, text, user, number, file, file_rand) VALUES (12, '/2019/8', 'FB', 6, 'Fuel for car from wednesday', '2019-08-25', '50 Liter fuel
Price: XY €', 'test', null, '01DemoFile.txt', null);
INSERT INTO documents (id, path, type, sender, title, date, text, user, number, file, file_rand) VALUES (13, '/2019/8', 'L', 5, 'Adobe Licence', '2019-08-25', 'Adobe CC Licence till 2020
Price: XY €', 'test', null, '01DemoFile.txt', null);
INSERT INTO documents (id, path, type, sender, title, date, text, user, number, file, file_rand) VALUES (14, '/2019/8', 'L', 9, 'Programm licence College', '2019-08-25', 'Free Licence from college
Expiration: 2020', 'test', null, '01DemoFile.txt', null);
INSERT INTO documents (id, path, type, sender, title, date, text, user, number, file, file_rand) VALUES (15, '/2019/8', 'I', 10, 'Rend Bill', '2019-08-25', 'Bill for August 2019
Need to pay', 'test', null, '01DemoFile.txt', null);
INSERT INTO documents (id, path, type, sender, title, date, text, user, number, file, file_rand) VALUES (16, '/2019/8', 'M', 8, 'Salary August 2019', '2019-08-25', 'Salarycheck', 'test', null, '01DemoFile.txt', null);
INSERT INTO documents (id, path, type, sender, title, date, text, user, number, file, file_rand) VALUES (17, '/2019/8', 'I', 3, 'Wheel change', '2019-08-25', 'Invoice for wheel change in March 2019
Price: XY€', 'test', null, '01DemoFile.txt', null);
INSERT INTO documents (id, path, type, sender, title, date, text, user, number, file, file_rand) VALUES (18, '/2019/8', 'B', 4, 'Abo check', '2019-08-25', 'Need to check my local supermarket abo', 'test', null, '01DemoFile.txt', null);
INSERT INTO documents (id, path, type, sender, title, date, text, user, number, file, file_rand) VALUES (19, '/2019/8', 'M', 7, 'Money back form event', '2019-08-25', 'Got some money back from the recent event in July 2019
Amount: XY €', 'test', null, '01DemoFile.txt', null);
INSERT INTO documents (id, path, type, sender, title, date, text, user, number, file, file_rand) VALUES (20, '/2019/8', 'B', 3, 'New Car licence', '2019-08-25', 'Licence to the new car', 'test', null, '01DemoFile.txt', null);

DROP TABLE IF EXISTS documents_tag;
create table documents_tag
(
  tag      varchar(50) not null,
  document int         not null,
    foreign key (document) references documents (id),
    foreign key (tag) references tag (tag)
);

INSERT INTO documents_tag (tag, document) VALUES ('Education', 13);
INSERT INTO documents_tag (tag, document) VALUES ('Finance', 13);
INSERT INTO documents_tag (tag, document) VALUES ('Car', 12);
INSERT INTO documents_tag (tag, document) VALUES ('Low Prio', 12);
INSERT INTO documents_tag (tag, document) VALUES ('Finance', 12);
INSERT INTO documents_tag (tag, document) VALUES ('Education', 14);
INSERT INTO documents_tag (tag, document) VALUES ('TODO', 15);
INSERT INTO documents_tag (tag, document) VALUES ('High Prio', 15);
INSERT INTO documents_tag (tag, document) VALUES ('Finance', 15);
INSERT INTO documents_tag (tag, document) VALUES ('Income', 16);
INSERT INTO documents_tag (tag, document) VALUES ('Finance', 16);
INSERT INTO documents_tag (tag, document) VALUES ('Car', 17);
INSERT INTO documents_tag (tag, document) VALUES ('Finance', 17);
INSERT INTO documents_tag (tag, document) VALUES ('TODO', 18);
INSERT INTO documents_tag (tag, document) VALUES ('High Prio', 18);
INSERT INTO documents_tag (tag, document) VALUES ('Form', 18);
INSERT INTO documents_tag (tag, document) VALUES ('Income', 19);
INSERT INTO documents_tag (tag, document) VALUES ('Finance', 19);
INSERT INTO documents_tag (tag, document) VALUES ('Car', 20);
INSERT INTO documents_tag (tag, document) VALUES ('High Prio', 20);