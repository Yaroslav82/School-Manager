create sequence users_seq start with 1 increment by 50;
create sequence roles_seq start with 1 increment by 50;
create table users (id integer not null, username varchar(255), password varchar(255), primary key (id));
create table roles (id integer not null, role_name varchar(255), primary key (id));

create table user_role_ref (id1 integer not null, id2 integer not null, primary key (id1, id2));
alter table if exists user_role_ref add constraint fk_users foreign key (id1) references users;
alter table if exists user_role_ref add constraint fk_roles foreign key (id2) references roles;

insert into roles (role_name)
values
('ROLE_STUDENT'), ('ROLE_TEACHER'), ('ROLE_ADMIN');