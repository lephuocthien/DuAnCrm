package com.myclass.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	public static Connection getConnection() {
		String database = "jdbc:mysql://localhost:3306/crm";
		String user ="root";
		String password ="12345678";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
<<<<<<< Updated upstream
//			Connection conn = DriverManager.getConnection(database, user, password);
=======
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/crm", "root", "12345678");
>>>>>>> Stashed changes
			return conn;
		} catch (ClassNotFoundException e) {
			System.out.println("Không tìm thấy Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Sai thông tin kết nối db!");
			e.printStackTrace();
		}
		return null;
	}
}
