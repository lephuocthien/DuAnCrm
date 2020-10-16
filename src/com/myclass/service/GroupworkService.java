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
			dtos.add(new GroupworkDto(groupwork.getId(), groupwork.getName(), groupwork.getStartDay(), groupwork.getEndDay()));
			
		}
		return dtos;
	}

	public void add(GroupworkDto dto) {
		Groupwork groupwork = new Groupwork();
		
		groupwork.setName(dto.getName());
		groupwork.setStartDay(dto.getStartDay());
		groupwork.setEndDay(dto.getEndDay());
		
		groupworkDao.add(groupwork);
	}

	public GroupworkDto getById(int id) {
		GroupworkDto dto = new GroupworkDto();

		Groupwork groupwork = groupworkDao.findById(id);

		dto.setId(groupwork.getId());
		dto.setName(groupwork.getName());
		dto.setStartDay(groupwork.getStartDay());
		dto.setEndDay(groupwork.getEndDay());

		return dto;
	}

	public void edit(GroupworkDto dto) {
		Groupwork groupwork = new Groupwork();
		groupwork.setId(dto.getId());
		groupwork.setName(dto.getName());
		groupwork.setStartDay(dto.getStartDay());
		groupwork.setEndDay(dto.getEndDay());

		groupworkDao.update(groupwork);
	}
	
}