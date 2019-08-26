CREATE DATABASE IF NOT EXISTS mydms
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
  id               int auto_increment
    primary key,
  path             varchar(128) null,
  type             varchar(3)   null,
  sender           int          null,
  title            varchar(64)  null,
  date             date         null,
  text             varchar(255) null,
  user             varchar(50)  null,
  number           varchar(64)  null,
  file             varchar(64)  null,
  invoice_nr       varchar(64)  null,
  invoice_price    double       null,
  invoice_date     date         null,
  invoice_detail   varchar(255) null,
  invoice_payment  varchar(32)  null,
  invoice_balanced tinyint(1)   null,
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

INSERT INTO mydms.documents (id, path, type, sender, title, date, text, user, number, file, invoice_nr, invoice_price, invoice_date, invoice_detail, invoice_payment, invoice_balanced) VALUES (12, '/2019/8', 'FB', 6, 'Fuel for car from wednesday', '2019-08-25', '50 Liter fuel
Price: XY €', 'test', null, '01DemoFile.txt', null, null, null, null, null, null);
INSERT INTO mydms.documents (id, path, type, sender, title, date, text, user, number, file, invoice_nr, invoice_price, invoice_date, invoice_detail, invoice_payment, invoice_balanced) VALUES (13, '/2019/8', 'L', 5, 'Adobe Licence', '2019-08-25', 'Adobe CC Licence till 2020
Price: XY €', 'test', null, '01DemoFile.txt', null, null, null, null, null, null);
INSERT INTO mydms.documents (id, path, type, sender, title, date, text, user, number, file, invoice_nr, invoice_price, invoice_date, invoice_detail, invoice_payment, invoice_balanced) VALUES (14, '/2019/8', 'L', 9, 'Programm licence College', '2019-08-25', 'Free Licence from college
Expiration: 2020', 'test', null, '01DemoFile.txt', null, null, null, null, null, null);
INSERT INTO mydms.documents (id, path, type, sender, title, date, text, user, number, file, invoice_nr, invoice_price, invoice_date, invoice_detail, invoice_payment, invoice_balanced) VALUES (15, '/2019/8', 'I', 10, 'Rend Bill', '2019-08-25', 'Bill for August 2019
Need to pay', 'test', null, '01DemoFile.txt', null, null, null, null, null, null);
INSERT INTO mydms.documents (id, path, type, sender, title, date, text, user, number, file, invoice_nr, invoice_price, invoice_date, invoice_detail, invoice_payment, invoice_balanced) VALUES (16, '/2019/8', 'M', 8, 'Salary August 2019', '2019-08-25', 'Salarycheck', 'test', null, '01DemoFile.txt', null, null, null, null, null, null);
INSERT INTO mydms.documents (id, path, type, sender, title, date, text, user, number, file, invoice_nr, invoice_price, invoice_date, invoice_detail, invoice_payment, invoice_balanced) VALUES (17, '/2019/8', 'I', 3, 'Wheel change', '2019-08-25', 'Invoice for wheel change in March 2019
Price: XY€', 'test', null, '01DemoFile.txt', null, null, null, null, null, null);
INSERT INTO mydms.documents (id, path, type, sender, title, date, text, user, number, file, invoice_nr, invoice_price, invoice_date, invoice_detail, invoice_payment, invoice_balanced) VALUES (18, '/2019/8', 'B', 4, 'Abo check', '2019-08-25', 'Need to check my local supermarket abo', 'test', null, '01DemoFile.txt', null, null, null, null, null, null);
INSERT INTO mydms.documents (id, path, type, sender, title, date, text, user, number, file, invoice_nr, invoice_price, invoice_date, invoice_detail, invoice_payment, invoice_balanced) VALUES (19, '/2019/8', 'M', 7, 'Money back form event', '2019-08-25', 'Got some money back from the recent event in July 2019
Amount: XY €', 'test', null, '01DemoFile.txt', null, null, null, null, null, null);
INSERT INTO mydms.documents (id, path, type, sender, title, date, text, user, number, file, invoice_nr, invoice_price, invoice_date, invoice_detail, invoice_payment, invoice_balanced) VALUES (20, '/2019/8', 'B', 3, 'New Car licence', '2019-08-25', 'Licence to the new car', 'test', null, '01DemoFile.txt', null, null, null, null, null, null);
create table documents_tag
(
  tag      varchar(50) not null,
  document int         not null,
  constraint documents_tag_documents_id_fk
    foreign key (document) references documents (id),
  constraint documents_tag_tag_tag_fk
    foreign key (tag) references tag (tag)
);

INSERT INTO mydms.documents_tag (tag, document) VALUES ('Education', 13);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Finance', 13);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Car', 12);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Low Prio', 12);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Finance', 12);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Education', 14);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('TODO', 15);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('High Prio', 15);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Finance', 15);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Income', 16);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Finance', 16);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Car', 17);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Finance', 17);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('TODO', 18);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('High Prio', 18);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Form', 18);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Income', 19);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Finance', 19);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('Car', 20);
INSERT INTO mydms.documents_tag (tag, document) VALUES ('High Prio', 20);
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

INSERT INTO mydms.sender (id, name, text, date, user) VALUES (2, 'Bank', 'Private Bank', '2019-08-25', 'test');
INSERT INTO mydms.sender (id, name, text, date, user) VALUES (3, 'Car Service', 'Car Service of XY', '2019-08-25', 'test');
INSERT INTO mydms.sender (id, name, text, date, user) VALUES (4, 'Supermarket', 'Nearby Supermarket', '2019-08-25', 'test');
INSERT INTO mydms.sender (id, name, text, date, user) VALUES (5, 'Online Shop XY', 'Online Shop XY', '2019-08-25', 'test');
INSERT INTO mydms.sender (id, name, text, date, user) VALUES (6, 'Fuel Station', 'Fuel Station XY', '2019-08-25', 'test');
INSERT INTO mydms.sender (id, name, text, date, user) VALUES (7, 'School', 'High school stuff', '2019-08-25', 'test');
INSERT INTO mydms.sender (id, name, text, date, user) VALUES (8, 'Employer XY', 'Stuff from the company', '2019-08-25', 'test');
INSERT INTO mydms.sender (id, name, text, date, user) VALUES (9, 'College XY', 'College XY Related Stuff', '2019-08-25', 'test');
INSERT INTO mydms.sender (id, name, text, date, user) VALUES (10, 'Landlord XY', 'Landlord XY', '2019-08-25', 'test');
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

INSERT INTO mydms.tag (tag, color, user, text, date) VALUES ('Car', 'badge-secondary', 'test', 'Car related stuff', '2019-08-25');
INSERT INTO mydms.tag (tag, color, user, text, date) VALUES ('Education', 'badge-warning', 'test', 'Education realted stuff', '2019-08-25');
INSERT INTO mydms.tag (tag, color, user, text, date) VALUES ('Finance', 'badge-primary', 'test', 'Everything finance related', '2019-08-25');
INSERT INTO mydms.tag (tag, color, user, text, date) VALUES ('Form', 'badge-dark', 'test', 'Forms', '2019-08-25');
INSERT INTO mydms.tag (tag, color, user, text, date) VALUES ('High Prio', 'badge-danger', 'test', 'Important stuff', '2019-08-25');
INSERT INTO mydms.tag (tag, color, user, text, date) VALUES ('Income', 'badge-success', 'test', 'Stuff i got money from', '2019-08-25');
INSERT INTO mydms.tag (tag, color, user, text, date) VALUES ('Low Prio', 'badge-light', 'test', 'Stuff thats quite irrelevant', '2019-08-25');
INSERT INTO mydms.tag (tag, color, user, text, date) VALUES ('TODO', 'badge-info', 'test', 'Stuff that needs some work', '2019-08-25');
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

INSERT INTO mydms.types (short_name, name, text, user, date) VALUES ('B', 'Blanko Document', 'Everything that doesnt fit somewhere else', 'test', '2019-08-25');
INSERT INTO mydms.types (short_name, name, text, user, date) VALUES ('FB', 'Fuel Bill', 'Bills from purchasing fuel', 'test', '2019-08-25');
INSERT INTO mydms.types (short_name, name, text, user, date) VALUES ('I', 'Invoice', 'Invoices', 'test', '2019-08-25');
INSERT INTO mydms.types (short_name, name, text, user, date) VALUES ('L', 'Licence', 'Licences', 'test', '2019-08-25');
INSERT INTO mydms.types (short_name, name, text, user, date) VALUES ('M', 'Paycheck', 'Paychecks received', 'test', '2019-08-25');
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

INSERT INTO mydms.users (username, password, enabled, name, date, email) VALUES ('test', '{noop}test123', 1, 'test user', '2019-08-07', 'test@mail.com');