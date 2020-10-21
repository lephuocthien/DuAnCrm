package com.myclass.dto;

import java.util.Date;

public class TaskDto {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private int userId;
	private String userName;
	private int groupworkId;
	private String groupworkName;
	private int statusId;
	private String statusName;
	
	public TaskDto() {
	}
	public TaskDto(int id, String name, Date startDate, Date endDate, int userId, String userName, int groupworkId,
			String groupworkName, int statusId, String statusName) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.userName = userName;
		this.groupworkId = groupworkId;
		this.groupworkName = groupworkName;
		this.statusId = statusId;
		this.statusName = statusName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getGroupworkId() {
		return groupworkId;
	}
	public void setGroupworkId(int groupworkId) {
		this.groupworkId = groupworkId;
	}
	public String getGroupworkName() {
		return groupworkName;
	}
	public void setGroupworkName(String groupworkName) {
		this.groupworkName = groupworkName;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
	
}
