insert into major_table values (1,'지능기전');
insert into council_table values (1,now(),now(),'루리','test',7,1);


insert into department_table values (1,now(),now(),'복지',1);
insert into department_table values (2,now(),now(),'문화',1);
insert into department_table values (3,now(),now(),'기획',1);

insert into promise_table values(1,now(),now(),'복지1',false,1);
insert into promise_table values(2,now(),now(),'복지2',false,1);
insert into promise_table values(3,now(),now(),'복지3',true,1);
insert into promise_table values(4,now(),now(),'뮨화1',false,2);
insert into promise_table values(5,now(),now(),'문화2',true,2);
insert into promise_table values(6,now(),now(),'기획1',false,3);
insert into promise_table values(7,now(),now(),'기획2',false,3);
insert into promise_table values(8,now(),now(),'기획3',false,3);
insert into promise_table values(9,now(),now(),'기획4',true,3);

insert into user_table(STUDENT_NUM, NAME, CREATED_TIME,MODIFIED_TIME,GRADE,ROLE,STATUS,MAJOR_ID) values('19011721', '이재표', now(),now(),1,'ROLE_ADMIN','재학',1);
INSERT INTO vote_table VALUES (1, 0, CURRENT_DATE + 1, 0);
insert into board_table values (1,now(),now(),'Council','test','test','test',1,'19011721',1);