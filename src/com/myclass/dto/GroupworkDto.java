package com.myclass.dto;

public class GroupworkDto {
	private int id;
	private String name;
	private String startDay;
	private String endDay;
	private int statusId;
	private int userId;
	private int taskId;
	private String statusName;
	private String userName;
	private String taskName;
	
	public GroupworkDto() {}

	public GroupworkDto(int id, String name, String startDay, String endDay, int statusId, int userId, int taskId) {
		super();
		this.id = id;
		this.name = name;
		this.startDay = startDay;
		this.endDay = endDay;
		this.statusId = statusId;
		this.userId = userId;
		this.taskId = taskId;
	}
	
	

	public GroupworkDto(int id, String name, String startDay, String endDay, int statusId, int userId, int taskId,
			String statusName, String userName, String taskName) {
		super();
		this.id = id;
		this.name = name;
		this.startDay = startDay;
		this.endDay = endDay;
		this.statusId = statusId;
		this.userId = userId;
		this.taskId = taskId;
		this.statusName = statusName;
		this.userName = userName;
		this.taskName = taskName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
}
