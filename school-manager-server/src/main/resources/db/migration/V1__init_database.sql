create sequence homeworks_seq start with 1 increment by 50;
create sequence lessons_seq start with 1 increment by 50;
create sequence students_seq start with 1 increment by 50;
create table homeworks (id integer not null, deadline varchar(255), description varchar(255), group_name varchar(255), name varchar(255), subject varchar(255), primary key (id));
create table lessons (id integer not null, group_name varchar(255), subject varchar(255), timestamp varchar(255), primary key (id));
create table students (id integer not null, first_name varchar(255), group_name varchar(255), last_name varchar(255), primary key (id));
create table student_homework_ref (id1 integer not null, id2 integer not null, primary key (id1, id2));
alter table if exists student_homework_ref add constraint fk_homework foreign key (id2) references homeworks;
alter table if exists student_homework_ref add constraint fk_student foreign key (id1) references students;
