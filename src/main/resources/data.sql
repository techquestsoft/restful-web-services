DROP TABLE IF EXISTS User1;

CREATE TABLE User1 (
	id integer not null,
	dob timestamp,
	name varchar(255),
	primary key (id)
);

CREATE TABLE post (
	id integer not null,
	description varchar(255),
	user_id integer,
	primary key (id)
);

insert into user1 values(101, CURRENT_TIMESTAMP, 'John');
insert into user1 values(102, CURRENT_TIMESTAMP, 'Robert');
insert into user1 values(104, CURRENT_TIMESTAMP, 'Andrew');
insert into user1 values(105, CURRENT_TIMESTAMP, 'Jack');
insert into post values(111, 'first post', 101);
insert into post values(112, 'second post', 101);
insert into post values(113, 'third post', 104);


