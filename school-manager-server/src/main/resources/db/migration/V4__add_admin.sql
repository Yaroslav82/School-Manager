insert into users (id, username, password)
values
(1, 'admin', '$2a$10$119vmaGqT0zeAY0gBIENnuNJcu7QnIwq91MP7imO5JiTIUya92VX2'); -- password: admin

insert into user_role_ref (id1, id2)
values
(1, 3);