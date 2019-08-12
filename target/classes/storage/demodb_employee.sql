create table employee
(
  id         int auto_increment
    primary key,
  first_name varchar(64)  null,
  last_name  varchar(64)  null,
  email      varchar(128) null
);

INSERT INTO demodb.employee (id, first_name, last_name, email) VALUES (1, 'test', 'tttzzz', 'testtt');