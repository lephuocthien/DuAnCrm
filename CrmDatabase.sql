use crm;

CREATE TABLE IF NOT EXISTS roles (
	id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
	id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    avatar VARCHAR(100),
    role_id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS groupworks (
	id INT NOT NULL auto_increment,
    name varchar(100) not null,
    startDay date not null,
    endDay date not null,
    status_id int,
    user_id int,
    task_id int,
    primary key (id)
);

create table if not exists statuses (
	id int not null auto_increment,
    name varchar(100) not null,
    primary key (id)
);

create table if not exists tasks (
	id int not null,
    name varchar(100) not null,
    startDay date not null,
    endDay date not null,
    primary key (id)
);


ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles (id)  ON DELETE CASCADE;

INSERT INTO roles( name, description ) VALUES ("ROLE_ADMIN", "Quản trị hệ thống");
INSERT INTO roles( name, description ) VALUES ("ROLE_MANAGER", "Quản lý");
INSERT INTO roles( name, description ) VALUES ("ROLE_USER", "Nhân viên");

alter table groupworks add foreign key (user_id) references users (id);
alter table groupworks add foreign key (task_id) references tasks (id);
alter table groupworks add foreign key (status_id) references statuses (id);