create sequence seq_master;
create sequence seq_detail;

drop table t_디테일;
drop table t_master;

create table t_master(
	id			number(9) 		primary key,
	name		varchar2(500)
);

create table t_디테일(
	id			number(9) 		primary key,
	master_id	number(9)		not null --references t_master(id),
	d_name		varchar2(500)
);

--시퀀스 활용 예시
select seq_master.nextval from dual;

insert into t_master(id, name) values(seq_master.nextval, '홍길동');	--13으로 등록됨 
select * from t_master;

insert into t_디테일(id, master_id, d_name) values(seq_detail.nextval, 0, '홍길동의 왼팔');






