package com.myclass.dto;

import java.sql.Date;

public class GroupworkDto {
	private int id;
	private String name;
	private String startDay;
	private String endDay;
	
	public GroupworkDto() {}
	
	public GroupworkDto(int id, String name, String startDay, String endDay) {
		super();
		this.id = id;
		this.name = name;
		this.startDay = startDay;
		this.endDay = endDay;
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

	public void setStartDay(String startDay2) {
		this.startDay = startDay2;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay2) {
		this.endDay = endDay2;
	}
}
