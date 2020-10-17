package com.myclass.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.myclass.dao.GroupworkDao;
import com.myclass.dto.GroupworkDto;
import com.myclass.entity.Groupwork;

public class GroupworkService {

	private GroupworkDao groupworkDao = null;

	public GroupworkService() {
		groupworkDao = new GroupworkDao();
		
	}
	
	public List<GroupworkDto> getAll() throws ParseException {
		List<GroupworkDto> dtos = new ArrayList<GroupworkDto>();
		
		List<Groupwork> groupworks = groupworkDao.findAll();
		
		for(Groupwork groupwork : groupworks) {
			dtos.add(new GroupworkDto(
					groupwork.getId(), 
					groupwork.getName(), 
					convertIn(groupwork.getStartDay()), 
					convertIn(groupwork.getEndDay())
					));
		}
		return dtos;
	}

	public void add(GroupworkDto dto) throws ParseException {
		Groupwork groupwork = new Groupwork();
		
		groupwork.setName(dto.getName());
		groupwork.setStartDay(convertOut(dto.getStartDay()));
		groupwork.setEndDay(convertOut(dto.getEndDay()));
		
		groupworkDao.add(groupwork);
	}

	public GroupworkDto getById(int id) throws ParseException {
		GroupworkDto dto = new GroupworkDto();

		Groupwork groupwork = groupworkDao.findById(id);

		dto.setId(groupwork.getId());
		dto.setName(groupwork.getName());
		dto.setStartDay(convertIn(groupwork.getStartDay()));
		dto.setEndDay(convertIn(groupwork.getEndDay()));

		return dto;
	}

	public void edit(GroupworkDto dto) throws ParseException {
		Groupwork groupwork = new Groupwork();
		groupwork.setId(dto.getId());
		groupwork.setName(dto.getName());
		groupwork.setStartDay(convertOut(dto.getStartDay()));
		groupwork.setEndDay(convertOut(dto.getEndDay()));

		groupworkDao.update(groupwork);
	}

	public void removeById(int id) {
		groupworkDao.deleteById(id);
	}
	
	// ex: inDate = "2001-01-13" => outDate = "13/01/2001"
	public String convertIn(String inDate) throws ParseException {
		String outDate = "";
		
		SimpleDateFormat inSDF = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat outSDF = new SimpleDateFormat("dd/mm/yyyy");
		
		Date date = inSDF.parse(inDate);
		outDate = outSDF.format(date);
		
		return outDate;
	}
	
	// ex: inDate = "13/01/2001" => outDate = "2001-01-13"
	public String convertOut(String inDate) throws ParseException {
		String outDate = "";
		
		SimpleDateFormat inSDF = new SimpleDateFormat("dd/mm/yyyy");
		SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");
		
		Date date = inSDF.parse(inDate);
		outDate = outSDF.format(date);
		
		return outDate;
	}
}