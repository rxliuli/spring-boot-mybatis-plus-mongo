create schema spring_boot_mybatis_plus_mongo;
use spring_boot_mybatis_plus_mongo;

create table user_info (
  id   bigint primary key not null,
  name varchar(20)        not null,
  age  tinyint            not null,
  sex  bool               not null
);
