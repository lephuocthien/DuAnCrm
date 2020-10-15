package com.myclass.entity;

public class Task {
	private int id;
	private String startDay;
	private String endDay;
	
	public Task() {}
	
	public Task(int id, String startDay, String endDay) {
		this.id = id; 
		this.startDay = startDay;
		this.endDay = endDay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
