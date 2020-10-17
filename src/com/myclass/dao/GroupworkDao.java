package com.myclass.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.entity.Groupwork;
import com.myclass.entity.Role;

public class GroupworkDao {

	public List<Groupwork> findAll() {
		List<Groupwork> groupworks = new ArrayList<Groupwork>();
		
		try(Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM groupworks");
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Groupwork groupwork = new Groupwork();
				
				groupwork.setId(resultSet.getInt("id"));
				groupwork.setName(resultSet.getString("name"));
				groupwork.setStartDay(resultSet.getString("start_date"));
				groupwork.setEndDay(resultSet.getString("end_date"));
				
				groupworks.add(groupwork);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return groupworks;
	}

	public void add(Groupwork groupwork) {
		String query = "INSERT INTO groupworks(name, start_date, end_date) VALUES (?, ?, ?)";
		
		try (Connection conn = JDBCConnection.getConnection()){
			
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, groupwork.getName());
			statement.setDate(2, Date.valueOf(groupwork.getStartDay()));
			statement.setDate(3, Date.valueOf(groupwork.getEndDay()));
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Groupwork findById(int id) {
		Groupwork groupwork = new Groupwork(); 
		try (Connection conn = JDBCConnection.getConnection()){
			
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM groupworks WHERE id = ?");
			
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				groupwork.setId(resultSet.getInt("id"));
				groupwork.setName(resultSet.getString("name"));
				groupwork.setStartDay(resultSet.getString("start_date"));
				groupwork.setEndDay(resultSet.getString("end_date"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return groupwork;
	}

	public void update(Groupwork groupwork) {
		String query = "UPDATE groupworks SET name = ?, start_date = ?, end_date = ? WHERE id = ?";
		
		try (Connection conn = JDBCConnection.getConnection()){

			PreparedStatement statement = conn.prepareStatement(query);

			statement.setString(1, groupwork.getName());
			statement.setDate(2, Date.valueOf(groupwork.getStartDay()));
			statement.setDate(3, Date.valueOf(groupwork.getEndDay()));
			statement.setInt(4, groupwork.getId());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void deleteById(int id) {
		String query = "DELETE FROM groupworks WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()){

			PreparedStatement statement = conn.prepareStatement(query);

			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
