package com.myclass.service;

import com.myclass.dao.GroupworkDao;

public class GroupworkService {
	
	private GroupworkDao groupworkDao = null;
	
	public GroupworkService() {
		groupworkDao = new GroupworkDao();
	}
}
