package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dao.GroupworkDao;
import com.myclass.dto.GroupworkDto;
import com.myclass.entity.Groupwork;

public class GroupworkService {
	
	private GroupworkDao groupworkDao = null;
	
	public GroupworkService() {
		groupworkDao = new GroupworkDao();
	}

	public List<GroupworkDto> getAll() {
		List<GroupworkDto> dtos = new ArrayList<GroupworkDto>();
		
		List<Groupwork> groupworks = groupworkDao.findAll();
		
		for(Groupwork groupwork : groupworks) {
			GroupworkDto dto = new GroupworkDto();
			
			dto.setId(groupwork.getId());
			dto.setName(groupwork.getName());
			dto.setStartDay(groupwork.getStartDay());
			dto.setEndDay(groupwork.getEndDay());
			dto.setStatusId(groupwork.getStatusId());
			dto.setUserId(groupwork.getUserId());
			dto.setTaskId(groupwork.getTaskId());
			
			dtos.add(dto);
		}
		
		return dtos;
	}

	public void save(GroupworkDto dto) {
		Groupwork groupwork = new Groupwork();
		
		groupwork.setName(dto.getName());
		groupwork.setStartDay(dto.getStartDay());
		groupwork.setEndDay(dto.getEndDay());
		groupwork.setStatusId(dto.getStatusId());
		groupwork.setUserId(dto.getUserId());
		groupwork.setTaskId(dto.getTaskId());
		
		groupworkDao.add(groupwork);
	}
}
