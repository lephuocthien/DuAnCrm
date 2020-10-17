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

	// PhÆ°Æ¡ng thá»©c láº¥y danh sĂ¡ch
	public List<User> findAll() {
		String query = "SELECT * FROM users";

		List<User> users = new ArrayList<User>();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Táº¡o cĂ¢u lá»‡nh truy váº¥n sá»­ dá»¥ng PreparedStatement
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
				// ThĂªm vĂ o danh sĂ¡ch
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	
	// PhÆ°Æ¡ ng thá»©c láº¥y danh sĂ¡ch tráº£ vá»� DTO
	public List<UserDto> findAllUserDtos() {
		String query = "SELECT u.id, u.email, u.fullname, r.description"
				+ " FROM users u JOIN roles r ON u.role_id = r.id";

		List<UserDto> users = new ArrayList<UserDto>();
		
		try (Connection conn = JDBCConnection.getConnection()) {
			// Táº¡o cĂ¢u lá»‡nh truy váº¥n sá»­ dá»¥ng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UserDto dto = new UserDto();
				dto.setId(resultSet.getInt("id"));
				dto.setFullname(resultSet.getString("fullname"));
				dto.setEmail(resultSet.getString("email"));
				dto.setRoleName(resultSet.getString("description"));
				// ThĂªm vĂ o danh sĂ¡ch
				users.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	// PhÆ°Æ¡ng thá»©c láº¥y ra Ä‘á»‘i tÆ°á»£ng role theo id
	public User findById(int id) {
		String query = "SELECT * FROM users WHERE id = ?";

		User user = new User();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Táº¡o cĂ¢u lá»‡nh truy váº¥n sá»­ dá»¥ng PreparedStatement
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
	
	// PhÆ°Æ¡ng thá»©c tĂ¬m kiáº¿m thĂ´ng tin user theo email
	public User findByEmail(String email) {
		String query = "SELECT * FROM users WHERE email = ?";
		
		User user = null;
		try (Connection conn = JDBCConnection.getConnection()) {
			// Táº¡o cĂ¢u lá»‡nh truy váº¥n sá»­ dá»¥ng PreparedStatement
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

	// PhÆ°Æ¡ng thá»©c thĂªm má»›i
	public void add(User user) {
		String query = "INSERT INTO users(email, password, fullname, avatar, role_id) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = JDBCConnection.getConnection()) {
			
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// PhÆ°Æ¡ng thá»©c cáº­p nháº­t
	public void update(User user) {
		String query = "UPDATE users SET email = ?, password = ?, fullname = ?, avatar = ?, role_id = ? WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {
			
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			statement.setInt(6, user.getId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void deleteById(int id) {
		String query = "DELETE FROM users WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(query);

			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
