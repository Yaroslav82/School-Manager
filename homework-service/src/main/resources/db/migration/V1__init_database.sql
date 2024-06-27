create sequence homeworks_seq start with 50 increment by 20;
create table homeworks (id integer not null, deadline varchar(255), description varchar(255), group_name varchar(255), name varchar(255), subject varchar(255), primary key (id));
