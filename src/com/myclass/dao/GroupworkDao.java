package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.entity.Groupwork;
import com.myclass.entity.Role;

public class GroupworkDao {

	// Phương thức lấy danh sách theo role
	public List<Groupwork> findAllByRole(int id) {
		List<Groupwork> groupworks = new ArrayList<Groupwork>();
		String query = "SELECT g.id as id_groupwork, g.name as name_groupwork, "
				+ "g.start_date as start_date_groupwork, g.end_date as end_date_groupwork "
				+ "FROM groupworks g join tasks t on g.id=t.groupwork_id where t.user_id=?";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			// Thực thi câu lệnh truy vấn
			// Tạo đối tượng ResultSet lưu trữ tạm thời dữ liệu lấy ra database
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				// Tạo đối tượng groupwork
				Groupwork groupwork = new Groupwork();
				groupwork.setId(resultSet.getInt("id_groupwork"));
				groupwork.setName(resultSet.getString("name_groupwork"));
				groupwork.setStartDate(resultSet.getDate("start_date_groupwork"));
				groupwork.setEndDate(resultSet.getDate("end_date_groupwork"));
				// Thêm vào danh sách
				groupworks.add(groupwork);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupworks;
	}

	// Phương thức lấy danh sách
	public List<Groupwork> findAll() {
		List<Groupwork> groupworks = new ArrayList<Groupwork>();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM groupworks");
			// Thực thi câu lệnh truy vấn
			// Tạo đối tượng ResultSet lưu trữ tạm thời dữ liệu lấy ra database
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				// Tạo đối tượng groupwork
				Groupwork groupwork = new Groupwork();
				groupwork.setId(resultSet.getInt("id"));
				groupwork.setName(resultSet.getString("name"));
				groupwork.setStartDate(resultSet.getDate("start_date"));
				groupwork.setEndDate(resultSet.getDate("end_date"));
				// Thêm vào danh sách
				groupworks.add(groupwork);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupworks;
	}

	// Phương thức lấy ra đối tượng theo id
	public Groupwork findById(int id) {
		// Tạo đối tượng groupwork
		Groupwork groupwork = new Groupwork();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM groupworks WHERE id = ?");
			// Thay thế dấu ? bằng id truyền lên từ servlet
			statement.setInt(1, id);
			// Thực thi câu lệnh truy vấn
			// Tạo đối tượng ResultSet lưu trữ tạm thời dữ liệu lấy ra database
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				groupwork.setId(resultSet.getInt("id"));
				groupwork.setName(resultSet.getString("name"));
				groupwork.setStartDate(resultSet.getDate("start_date"));
				groupwork.setEndDate(resultSet.getDate("end_date"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupwork;
	}

	// Phương thức thêm mới
	public void add(Groupwork groupwork) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String query = "INSERT INTO groupworks(name, start_date, end_date) "
				+ "VALUES (?, STR_TO_DATE(?,'%d/%m/%Y'), STR_TO_DATE(?,'%d/%m/%Y'))";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			// Thay thế dấu ? bằng dữ liệu lấy ra từ đối tượng groupwork
			statement.setString(1, groupwork.getName());
			statement.setString(2, df.format(groupwork.getStartDate()));
			statement.setString(3, df.format(groupwork.getEndDate()));
			// Thực thi câu lệnh truy vấn
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Phương thức cập nhật
	public void update(Groupwork groupwork) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String query = "UPDATE groupworks SET name = ?, " + "start_date = STR_TO_DATE(?,'%d/%m/%Y'), "
				+ "end_date = STR_TO_DATE(?,'%d/%m/%Y') WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			// Thay thế dấu ? bằng dữ liệu lấy ra từ đối tượng groupwork
			statement.setString(1, groupwork.getName());
			statement.setString(2, df.format(groupwork.getStartDate()));
			statement.setString(3, df.format(groupwork.getEndDate()));
			statement.setInt(4, groupwork.getId());
			// Thực thi câu lệnh truy vấn
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Phương thức xóa đối tượng groupwork theo id
	public void deleteById(int id) {
		String query = "DELETE FROM groupworks WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			// Thay thế dấu ? bằng dữ liệu lấy ra từ đối tượng groupwork
			statement.setInt(1, id);
			// Thực thi câu lệnh truy vấn
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
