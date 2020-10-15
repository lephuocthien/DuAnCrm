package com.myclass.dto;

public class GroupworkDto {
	private int id;
	private String name;
	private String startDay;
	private String endDay;
	private int statusId;
	private int userId;
	private int taskId;
	
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
