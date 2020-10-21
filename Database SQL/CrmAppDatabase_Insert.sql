-- Các quyền
	INSERT INTO roles( name, description ) VALUES ("ROLE_ADMIN", "Quản trị hệ thống");
	INSERT INTO roles( name, description ) VALUES ("ROLE_MANAGER", "Quản lý");
	INSERT INTO roles( name, description ) VALUES ("ROLE_USER", "Nhân viên");
    
-- Các trạng thái
	INSERT INTO status( name ) VALUES ("Chưa thực hiện");
	INSERT INTO status( name ) VALUES ("Đang thực hiện");
	INSERT INTO status( name ) VALUES ("Đã hoàn thành");

-- Các user
	-- ROLE_ADMIN
		-- email: admin@gmail.com, password: admin123
		insert into users(email,password,fullname,avatar,role_id)values("admin@gmail.com","$2a$12$nniH84EWZwLPa2MRoNtguOwVTdPJvvBZj/P1w8VHx0fSH5hhaMG2y","Admin","Avatar",1);
	-- ROLE_MANAGER
		-- email: ql1@gmail.com, password: 1234
        insert into users(email,password,fullname,avatar,role_id)values("ql1@gmail.com","$2a$12$zMKlDPmzq5WbHMQhz1ZYr.//eX8vKPxJ2buwtLv4S5DkzgKMmrSCy","Quản lý 1","Avatar",2);
        -- email: ql2@gmail.com, password: 1234
        insert into users(email,password,fullname,avatar,role_id)values("ql2@gmail.com","$2a$12$GlwXte/65jrEhask.A.J5e8W69XFOhJ7KBesVgKWmWkkH7yh2085a","Quản lý 2","Avatar",2);
	-- ROLE_USER
		-- email: nv1@gmail.com, password: 1234
         insert into users(email,password,fullname,avatar,role_id)values("nv1@gmail.com","$2a$12$ToIVd83rjqzCjcOafkM/n.Vi8NicaClrJNqWRjKiK2TmPSVAPIwei","Nhân viên 1","Avatar",3);
        -- email: nv2@gmail.com, password: 1234
        insert into users(email,password,fullname,avatar,role_id)values("nv2@gmail.com","$2a$12$ySlzZhn5V35BbylwCzUdt.2SLpGSHSCydMI7WMjemWumFPkuLvghm","Nhân viên 2","Avatar",3);
        -- email: nv3@gmail.com, password: 1234
         insert into users(email,password,fullname,avatar,role_id)values("nv3@gmail.com","$2a$12$rTsFzD0dX0SQEVv4j4c2oehgCr4y4V8DLsUF2sS6ioXkrf.ajPWL.","Nhân viên 3","Avatar",3);
        -- email: nv4@gmail.com, password: 1234
         insert into users(email,password,fullname,avatar,role_id)values("nv4@gmail.com","$2a$12$UIZhQoImOqHZAksW3GfIXu.b8cUGTdfe4.g.5rNVCbgT.YytPJGIi","Nhân viên 4","Avatar",3);
         
-- Các dự án
	insert into groupworks(name, start_date, end_date) values("Dự án 1",STR_TO_DATE('16/9/2020','%d/%m/%Y'),STR_TO_DATE('16/10/2020','%d/%m/%Y'));
    insert into groupworks(name, start_date, end_date) values("Dự án 2",STR_TO_DATE('16/9/2020','%d/%m/%Y'),STR_TO_DATE('16/10/2020','%d/%m/%Y'));
    
-- Các công việc
	-- Dự án 1: Đồ án crm , Manager: Quản lý 1, User: Nhân viên 1, Nhân viên 2
		insert into tasks(name, start_date, end_date, user_id, groupwork_id, status_id)values("Quản lý dự án 1", STR_TO_DATE('16/9/2020','%d/%m/%Y'),STR_TO_DATE('16/10/2020','%d/%m/%Y'),2,1,2 );
		insert into tasks(name, start_date, end_date, user_id, groupwork_id, status_id)values("Phân tích dự án 1", STR_TO_DATE('16/9/2020','%d/%m/%Y'),STR_TO_DATE('16/10/2020','%d/%m/%Y'),4,1,2 );
		insert into tasks(name, start_date, end_date, user_id, groupwork_id, status_id)values("Phân tích database 1", STR_TO_DATE('16/9/2020','%d/%m/%Y'),STR_TO_DATE('16/10/2020','%d/%m/%Y'),5,1,3 );
	-- Dự án 1: Quản lý bán hàng, Manager: Quản lý 2, User: Nhân viên 3, Nhân viên 4
		insert into tasks(name, start_date, end_date, user_id, groupwork_id, status_id)values("Quản lý dự án 2", STR_TO_DATE('16/10/2020','%d/%m/%Y'),STR_TO_DATE('16/11/2020','%d/%m/%Y'),3,2,2 );
		insert into tasks(name, start_date, end_date, user_id, groupwork_id, status_id)values("Phân tích dự án 2", STR_TO_DATE('16/10/2020','%d/%m/%Y'),STR_TO_DATE('16/11/2020','%d/%m/%Y'),6,2,2 );
		insert into tasks(name, start_date, end_date, user_id, groupwork_id, status_id)values("Phân tích database 2", STR_TO_DATE('16/10/2020','%d/%m/%Y'),STR_TO_DATE('16/11/2020','%d/%m/%Y'),7,2,3 );