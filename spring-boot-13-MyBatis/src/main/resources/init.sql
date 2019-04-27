
create table user_info
(
  id       int          not null primary key auto_increment,
  username varchar(500) null,
  usertype varchar(500) null,
  enabled  int          null,
  realname varchar(500) null,
  qq       varchar(500) null,
  tel      varchar(500) null,
  email    varchar(500) null
);

create table user_password
(
  id       int          not null primary key auto_increment,
  userid   int         null,
  password varchar(500) null
);


delete from user_info;
delete from user_password;
