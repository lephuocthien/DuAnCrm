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
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Groupwork;
import com.myclass.entity.Task;
import com.myclass.entity.Status;

public class TaskDao {

	// Phương thức lấy danh sách trạng thái
	public List<Status> findAllStatus() {
		List<Status> statuss = new ArrayList<Status>();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM status");
			// Thực thi câu lệnh truy vấn
			// Tạo đối tượng ResultSet lưu trữ tạm thời dữ liệu lấy ra database
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				// Tạo đối tượng task
				Status status = new Status();
				status.setId(resultSet.getInt("id"));
				status.setName(resultSet.getString("name"));
				// Thêm vào danh sách
				statuss.add(status);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statuss;
	}

	// Phương thức lấy đối tượng trạng thái theo id
	public Status findStatusById(int id) {
		// Tạo đối tượng groupwork
		Status status = new Status();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM status WHERE id = ?");
			// Thay thế dấu ? bằng id truyền lên từ servlet
			statement.setInt(1, id);
			// Thực thi câu lệnh truy vấn
			// Tạo đối tượng ResultSet lưu trữ tạm thời dữ liệu lấy ra database
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				status.setId(resultSet.getInt("id"));
				status.setName(resultSet.getString("name"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	// Phương thức lấy danh sách trả về DTO
	public List<TaskDto> findAllDtos() {
		String query = "SELECT t.id, t.name as task_name, g.name as groupwork_name, u.fullname as user_name, "
				+ "t.start_date, t.end_date, s.name as status_name, t.user_id "
				+ "FROM tasks t JOIN users u ON u.id = t.user_id " + "JOIN groupworks g ON g.id = t.groupwork_id "
				+ "JOIN status s ON s.id = t.status_id";

		List<TaskDto> tasks = new ArrayList<TaskDto>();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TaskDto dto = new TaskDto();
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("task_name"));
				dto.setGroupworkName(resultSet.getString("groupwork_name"));
				dto.setUserName(resultSet.getString("user_name"));
				dto.setStartDate(resultSet.getDate("start_date"));
				dto.setEndDate(resultSet.getDate("end_date"));
				dto.setStatusName(resultSet.getString("status_name"));
				dto.setUserId(resultSet.getInt("user_id"));
				// Thêm vào danh sách
				tasks.add(dto);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}

	// Phương thức lấy đối tượng trả về DTO theo id
	public TaskDto findDtosById(int id) {
		String query = "SELECT t.id, t.name as task_name, g.name as groupwork_name, u.fullname as user_name, "
				+ "t.start_date, t.end_date, s.name as status_name, t.user_id, t.status_id "
				+ "FROM tasks t JOIN users u ON u.id = t.user_id " + "JOIN groupworks g ON g.id = t.groupwork_id "
				+ "JOIN status s ON s.id = t.status_id " + "WHERE t.id=?";

		TaskDto dto = new TaskDto();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {

				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("task_name"));
				dto.setGroupworkName(resultSet.getString("groupwork_name"));
				dto.setUserId(resultSet.getInt("user_id"));
				dto.setUserName(resultSet.getString("user_name"));
				dto.setStartDate(resultSet.getDate("start_date"));
				dto.setEndDate(resultSet.getDate("end_date"));
				dto.setStatusId(resultSet.getInt("status_id"));
				dto.setStatusName(resultSet.getString("status_name"));

				// Thêm vào danh sách
				break;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	//Phương thức lấy danh sách công việc của user sách trả về DTO bằng id của user
	public List<TaskDto> findAllDtosByUser(int id) {
		List<TaskDto> tasks = new ArrayList<TaskDto>();
		String query = "SELECT t.id, t.name as task_name, g.name as groupwork_name, "
				+ "u.fullname as user_name, t.start_date, t.end_date, s.name as status_name, t.user_id "
				+ "	FROM tasks t JOIN users u ON u.id = t.user_id  " + "	JOIN groupworks g ON g.id = t.groupwork_id "
				+ "JOIN status s ON s.id = t.status_id " + "where t.user_id=?";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			// Thực thi câu lệnh truy vấn
			// Tạo đối tượng ResultSet lưu trữ tạm thời dữ liệu lấy ra database
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				TaskDto dto = new TaskDto();
				dto.setId(resultSet.getInt("id"));
				dto.setName(resultSet.getString("task_name"));
				dto.setGroupworkName(resultSet.getString("groupwork_name"));
				dto.setUserName(resultSet.getString("user_name"));
				dto.setStartDate(resultSet.getDate("start_date"));
				dto.setEndDate(resultSet.getDate("end_date"));
				dto.setStatusName(resultSet.getString("status_name"));
				dto.setUserId(resultSet.getInt("user_id"));
				// Thêm vào danh sách
				tasks.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}

	//Phương thức lấy danh sách
	public List<Task> findAll() {
		List<Task> tasks = new ArrayList<Task>();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM tasks");
			// Thực thi câu lệnh truy vấn
			// Tạo đối tượng ResultSet lưu trữ tạm thời dữ liệu lấy ra database
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				// Tạo đối tượng task
				Task task = new Task();
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setStartDate(resultSet.getDate("start_date"));
				task.setEndDate(resultSet.getDate("end_date"));
				task.setUserId(resultSet.getInt("user_id"));
				task.setGroupworkId(resultSet.getInt("groupwork_id"));
				task.setStatusId(resultSet.getInt("status_id"));
				// Thêm vào danh sách
				tasks.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tasks;
	}
	
	//Phương thức lấy đối tượng theo id
	public Task findById(int id) {
		Task task = new Task();
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM tasks WHERE id=?");
			// Thay thế dấu ? bằng id truyền lên từ servlet
			statement.setInt(1, id);
			// Thực thi câu lệnh truy vấn
			// Tạo đối tượng ResultSet lưu trữ tạm thời dữ liệu lấy ra database
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				// Tạo đối tượng task
				task.setId(resultSet.getInt("id"));
				task.setName(resultSet.getString("name"));
				task.setStartDate(resultSet.getDate("start_date"));
				task.setEndDate(resultSet.getDate("end_date"));
				task.setUserId(resultSet.getInt("user_id"));
				task.setGroupworkId(resultSet.getInt("groupwork_id"));
				task.setStatusId(resultSet.getInt("status_id"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return task;
	}

	//Phương thức thêm mới
	public void add(Task task) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String query = "insert into tasks(name, start_date, end_date, user_id, groupwork_id, status_id)"
				+ "values(?, STR_TO_DATE(?,'%d/%m/%Y'),STR_TO_DATE(?,'%d/%m/%Y'),?,?,1 )";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			// Thay thế dấu ? bằng dữ liệu lấy ra từ đối tượng groupwork
			statement.setString(1, task.getName());
			statement.setString(2, df.format(task.getStartDate()));
			statement.setString(3, df.format(task.getEndDate()));
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getGroupworkId());
			// Thực thi câu lệnh truy vấn
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Phương thức cập nhật
	public void update(Task task) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String query = "UPDATE tasks SET name = ?, " + "start_date = STR_TO_DATE(?,'%d/%m/%Y'), "
				+ "end_date = STR_TO_DATE(?,'%d/%m/%Y'), " + "user_id = ?, groupwork_id = ? WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			// Thay thế dấu ? bằng dữ liệu lấy ra từ đối tượng groupwork
			statement.setString(1, task.getName());
			statement.setString(2, df.format(task.getStartDate()));
			statement.setString(3, df.format(task.getEndDate()));
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getGroupworkId());
			statement.setInt(6, task.getId());
			// Thực thi câu lệnh truy vấn
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Phương thức cập nhật trạng thái của công việc
	public void updateStatus(Task task) {
		String query = "UPDATE tasks SET status_id=? WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {
			// Tạo câu lệnh truy vấn sử dụng PreparedStatement
			PreparedStatement statement = conn.prepareStatement(query);
			// Thay thế dấu ? bằng dữ liệu lấy ra từ đối tượng groupwork
			statement.setInt(1, task.getStatusId());
			statement.setInt(2, task.getId());
			// Thực thi câu lệnh truy vấn
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Phương thức xóa đối tượng groupwork theo id
	public void deleteById(int id) {
		String query = "DELETE FROM tasks WHERE id = ?";
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
