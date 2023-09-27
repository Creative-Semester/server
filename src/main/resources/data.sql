insert into major_table
values (1, '지능기전');
insert into major_table
values (2, '컴퓨터공학과');

insert into council_table
values (1, now(), now(), '루리', 'test', 7, 1);


insert into department_table
values (1, now(), now(), '복지', 1);
insert into department_table
values (2, now(), now(), '문화', 1);
insert into department_table
values (3, now(), now(), '기획', 1);

insert into promise_table
values (1, now(), now(), '복지1', false, 1);
insert into promise_table
values (2, now(), now(), '복지2', false, 1);
insert into promise_table
values (3, now(), now(), '복지3', true, 1);
insert into promise_table
values (4, now(), now(), '뮨화1', false, 2);
insert into promise_table
values (5, now(), now(), '문화2', true, 2);
insert into promise_table
values (6, now(), now(), '기획1', false, 3);
insert into promise_table
values (7, now(), now(), '기획2', false, 3);
insert into promise_table
values (8, now(), now(), '기획3', false, 3);
insert into promise_table
values (9, now(), now(), '기획4', true, 3);

<<<<<<< HEAD
insert into user_table(STUDENT_NUM, NAME, CREATED_TIME,MODIFIED_TIME,GRADE,ROLE,STATUS,MAJOR_ID) values('19011721', '이재표', now(),now(),1,'ROLE_ADMIN','재학',1);
INSERT INTO vote_table VALUES (1, 0, CURRENT_DATE + 1, 0);
insert into board_table values (1,now(),now(),'Council','test','test','test',1,'19011721',1);
=======
insert into user_table(STUDENT_NUM, CREATED_TIME, MODIFIED_TIME, GRADE, ROLE, STATUS, MAJOR_ID)
values ('19011721', now(), now(), 1, 'ROLE_ADMIN', '재학', 1);
insert into user_table(STUDENT_NUM, CREATED_TIME, MODIFIED_TIME, GRADE, ROLE, STATUS, MAJOR_ID)
values ('19011722', now(), now(), 1, 'ROLE_ADMIN', '재학', 1);
insert into user_table(STUDENT_NUM, CREATED_TIME, MODIFIED_TIME, GRADE, ROLE, STATUS, MAJOR_ID)
values ('19011723', now(), now(), 1, 'ROLE_ADMIN', '재학', 1);
insert into user_table(STUDENT_NUM, CREATED_TIME, MODIFIED_TIME, GRADE, ROLE, STATUS, MAJOR_ID)
values ('19011724', now(), now(), 1, 'ROLE_ADMIN', '재학', 1);

INSERT INTO vote_table
VALUES (1, 0, CURRENT_DATE + 1, 0);

insert into board_table
values (1, now(), now(), 'Council', 'test1', 'test1', 'test1', 1, '19011721', 1);
insert into board_table (ID, CREATED_TIME, MODIFIED_TIME, BOARD_TYPE, CONTENT, IMAGE, TITLE, MAJOR_ID, USER_ID)
values (2, now(), now(), 'Free', 'test2', 'test2', 'test2', 1, '19011722');

insert into board_table (ID, CREATED_TIME, MODIFIED_TIME, BOARD_TYPE, CONTENT, IMAGE, TITLE, MAJOR_ID, USER_ID)
values (3, now(), now(), 'Free', 'test3', 'test3', 'test3', 1, '19011723');

insert into board_table (ID, CREATED_TIME, MODIFIED_TIME, BOARD_TYPE, CONTENT, IMAGE, TITLE, MAJOR_ID, USER_ID)
values (4, now(), now(), 'Free', 'test4', 'test4', 'test4', 2, '19011724');
>>>>>>> dev
