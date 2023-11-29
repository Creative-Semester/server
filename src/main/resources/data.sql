insert into major_table(ID, NAME, SORT)
values (1, '지능기전공학부', 1);
insert into major_table(ID, NAME, SORT)
values (2, '무인이동체공학전공', 1);
insert into major_table(ID, NAME, SORT)
values (3, '스마트기기공학전공', 1);


insert into GRANTCODE_TABLE(ID, MAJOR_ID, GRANT_CODE)
values (1, 1, 'Jngj1');
insert into GRANTCODE_TABLE(ID, MAJOR_ID, GRANT_CODE)
values (2, 2, 'cptg2');
insert into GRANTCODE_TABLE(ID, MAJOR_ID, GRANT_CODE)
values (3, 3, 'dets3');

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


insert into user_table(STUDENT_NUM, NAME, CREATED_TIME, MODIFIED_TIME, GRADE, ROLE, STATUS, MAJOR_ID)
values ('19011721', '이재표', now(), now(), 1, 'ROLE_ADMIN', '재학', 1);
insert into user_table(STUDENT_NUM, NAME, CREATED_TIME, MODIFIED_TIME, GRADE, ROLE, STATUS, MAJOR_ID)
values ('19011729', '박정곤', now(), now(), 1, 'ROLE_USER', '재학', 3);


-- -- insert into board_table
-- -- values (1, now(), now(), 'Council', 'test1', 'test1', 'test1', 1, '19011721', 1);
-- insert into board_table (ID, CREATED_TIME, MODIFIED_TIME, BOARD_TYPE, CONTENT, IMAGE, TITLE, MAJOR_ID, USER_ID)
-- values (10, now(), now(), 'Free', 'test2', 'test2', 'test2', 1, '19011721');
--
-- INSERT INTO vote_table VALUES (100, 0, CURRENT_DATE + 1, 0);

--insert into FILE_TABLE(FILE_URL, FILE_NAME, PROFESSOR_ID)
--values('asdf', 'asev', 1)

--insert into PROFESSOR_TABLE(NAME, LOCATION, PHONENUM, EMAIL, MAJOR_SUB, LAB, INTRO, FILE, MAJOR_ID)
--values ('김세원', 'ai센터 524호', '02-6935-2678', 'sewonkim@sejong.ac.kr', 'Autonomous Ship', 'Autonomous Shipping Lab',
--        'Autonomous Vessel, Navigation Algorithm, Optimal Routind Algorithm을 연구하고 있습니다.', 1, 1);


--insert into COURSE_TABLE(PROFESSOR_ID, TITLE, CLASSIFICATION, GRADE, SCORE)
--values (1, '신호 및 시스템', '전선', '2학년', '3학점');
--insert into COURSE_TABLE(PROFESSOR_ID, TITLE, CLASSIFICATION, GRADE, SCORE)
--values (1, '인공지능', '전필', '3학년', '3학점');
