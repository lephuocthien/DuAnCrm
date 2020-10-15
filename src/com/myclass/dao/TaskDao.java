package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.entity.Task;

public class TaskDao {
	public List<Task> findAll() {
		List<Task> tasks = new ArrayList<Task>();
		String query = "SELECT * FROM tasks";
		
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Task task = new Task();
				
				task.setId(resultSet.getInt("id"));
				task.setStartDay(resultSet.getString("startDay"));
				task.setEndDay(resultSet.getString("endDay"));
				
				tasks.add(task);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tasks;
	}
}
