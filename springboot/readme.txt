- API:
	- Code 1 du an hoan thien: server & client
	- Login & register: keep ket noi
	- Cart/Checkout
- Spring boot & thymeleaf
	- Layout
		- Quan ly 1 vai chuc nang
	- Project:
		- Frontend -> Su dung template mau
		- Backend -> Code -> Su dung template mau
		- Cart/checkout (Cookie & Session)
	- Sercurity
	- Hibernate
================================================================
B1) Tao CSDL
create database BT2151

create table category (
	id int primary key auto_increment,
	name varchar(50)
);

create table news (
	id int primary key auto_increment,
	id_category int,
	title varchar(250),
	content longtext,
	thumbnail varchar(500),
	created_at datetime,
	updated_at datetime
);

alter table news
add constraint fk_category foreign key (id_category) references category (id);

B2) Tao du an -> Backend API
B3) API
	- Generate entities
	- JPA data controller
	- Mapping model <-> entities (Gen json -> ko gap van de ve data)
	- API Controller
		- API Category Controller
			- list
			- add
			- edit
			- delete
			- find
		- API News Controller
			->
B4) Client

B5) Client -> AngularJS