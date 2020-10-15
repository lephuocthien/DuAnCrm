package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.dto.GroupworkDto;
import com.myclass.entity.Groupwork;

public class GroupworkDao {

	public List<Groupwork> findAll() {
		List<Groupwork> groupworks = new ArrayList<Groupwork>();
		
		String query = "SELECT * FROM groupworks";
		
		try (Connection conn = JDBCConnection.getConnection()){
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Groupwork groupwork = new Groupwork();
				
				groupwork.setId(resultSet.getInt("id"));
				groupwork.setName(resultSet.getString("name"));
				groupwork.setStartDay(resultSet.getString("startDay"));
				groupwork.setEndDay(resultSet.getString("endDay"));
				groupwork.setStatusId(resultSet.getInt("statusId"));
				groupwork.setUserId(resultSet.getInt("userId"));
				groupwork.setTaskId(resultSet.getInt("taskId"));
				
				groupworks.add(groupwork);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return groupworks;
	}

	public void add(Groupwork groupwork) {
		String query = "INSERT INTO groupworks("
				+ "name, startDay, endDay, status_id, user_id, task_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, groupwork.getName());
			statement.setString(2, groupwork.getStartDay());
			statement.setString(3, groupwork.getEndDay());
			statement.setInt(4, groupwork.getStatusId());
			statement.setInt(5,  groupwork.getUserId());
			statement.setInt(6, groupwork.getTaskId());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
