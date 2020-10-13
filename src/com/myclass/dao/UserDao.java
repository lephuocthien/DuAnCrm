package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public class UserDao {

	// Phương thức lấy danh sách
	public List<User> findAll() {
		String query = "SELECT * FROM users";

		List<User> users = new ArrayList<User>();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setFullname(resultSet.getString("fullname"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setAvatar(resultSet.getString("avatar"));
				user.setRoleId(resultSet.getInt("role_id"));
				// Thêm vào danh sách
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	
	// Phươ ng thức lấy danh sách trả về DTO
	public List<UserDto> findAllUserDtos() {
		String query = "SELECT u.id, u.email, u.fullname, r.description"
				+ " FROM users u JOIN roles r ON u.role_id = r.id";

		List<UserDto> users = new ArrayList<UserDto>();
		
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UserDto dto = new UserDto();
				dto.setId(resultSet.getInt("id"));
				dto.setFullname(resultSet.getString("fullname"));
				dto.setEmail(resultSet.getString("email"));
				dto.setRoleName(resultSet.getString("description"));
				// Thêm vào danh sách
				users.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	// Phương thức lấy ra đối tượng role theo id
	public User findById(int id) {
		String query = "SELECT * FROM users WHERE id = ?";

		User user = new User();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setFullname(resultSet.getString("fullname"));
				user.setEmail(resultSet.getString("email"));
				user.setAvatar(resultSet.getString("avatar"));
				user.setRoleId(resultSet.getInt("role_id"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	// Phương thức tìm kiếm thông tin user theo email
	public User findByEmail(String email) {
		String query = "SELECT * FROM users WHERE email = ?";
		
		User user = null;
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, email);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setFullname(resultSet.getString("fullname"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setAvatar(resultSet.getString("avatar"));
				user.setRoleId(resultSet.getInt("role_id"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	// Phương thức thêm mới
	public void add(User user) {
		String query = "INSERT INTO users(email, password, fullname, avatar, role_id) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			// Thay thế dấu ? bằng dữ liệu lấy ra từ đối tượng role
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			// Thực thi câu lệnh truy vấn
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Phương thức cập nhật
	public void update(User user) {
		String query = "UPDATE users SET email = ?, password = ?, fullname = ?, avatar = ?, role_id = ? WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			// Thay thế dấu ? bằng dữ liệu lấy ra từ đối tượng role
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			statement.setInt(6, user.getId());
			// Thực thi câu lệnh truy vấn
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Phương thức xóa đối tượng role theo id
	public void deleteById(int id) {
		String query = "DELETE FROM users WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			// Thay thế dấu ? bằng dữ liệu lấy ra từ đối tượng role
			statement.setInt(1, id);
			// Thực thi câu lệnh truy vấn
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
