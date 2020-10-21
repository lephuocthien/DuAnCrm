package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dao.GroupworkDao;
import com.myclass.dao.TaskDao;
import com.myclass.dto.GroupworkDto;
import com.myclass.dto.RoleDto;
import com.myclass.dto.TaskDto;
import com.myclass.entity.Groupwork;
import com.myclass.entity.Role;

public class GroupworkService {
private GroupworkDao groupworkDao = null;
private TaskDao taskDao = null;

	
	public GroupworkService() {
		// TODO Auto-generated constructor stub
		groupworkDao = new GroupworkDao();
		taskDao = new TaskDao();
	}
	
	public List<GroupworkDto> getAllByRole(int id){
		List<GroupworkDto> dtos = new ArrayList<GroupworkDto>();
		List<Groupwork> groupworks = groupworkDao.findAllByRole(id);
		// Chuyển dữ liệu từ ENTITY qua DTO
		for (Groupwork groupwork : groupworks) {
			dtos.add(new GroupworkDto(groupwork.getId(), groupwork.getName(), groupwork.getStartDate(),groupwork.getEndDate()));
		}
		return dtos;
	}
	
	public List<GroupworkDto> getAll() {
		List<GroupworkDto> dtos = new ArrayList<GroupworkDto>();

		List<Groupwork> groupworks = groupworkDao.findAll();

		// Chuyển dữ liệu từ ENTITY qua DTO
		for (Groupwork groupwork : groupworks) {
			dtos.add(new GroupworkDto(groupwork.getId(), groupwork.getName(), groupwork.getStartDate(),groupwork.getEndDate()));
		}
		return dtos;
	}
	
	public GroupworkDto getById(int id) {
		GroupworkDto dto = new GroupworkDto();
		Groupwork groupwork = groupworkDao.findById(id);
		// Chuyển dữ liệu từ ENTITY qua DTO
		dto.setId(groupwork.getId());
		dto.setName(groupwork.getName());
		dto.setStartDate(groupwork.getStartDate());
		dto.setEndDate(groupwork.getEndDate());
		return dto;
	}
	
	//Phương thức tính % trạng thái công việc của dự án
	public List<Float> getAllPercent(int id){
		float completePercent = 0;//Đã hoàn thành
		float processPercent = 0;//Đang thực hiện
		float unfulfillPercent = 0;//Chưa hoàn thành
		float sumTasks = 0;//Tổng các công việc của dự án
		List<Float> percents = new ArrayList<Float>();//Lưu % trạng thái công việc của dự án
		List<TaskDto> tasks = taskDao.findAllDtos();// Tất cả các công việc
		Groupwork groupwork = groupworkDao.findById(id);// Dự án cần tính % trạng thái công việc
		for (TaskDto task : tasks) {
			//Nếu tên dự án trong task trùng với tên dự án cần tính thì xét trạng thái
			if (task.getGroupworkName().equals(groupwork.getName())) {
				if (task.getStatusName().equals("Chưa thực hiện")) {
					unfulfillPercent++;
				} else if (task.getStatusName().equals("Đang thực hiện")) {
					processPercent++;
				} else if (task.getStatusName().equals("Đã hoàn thành")) {
					completePercent++;
				}
			}
		}
		sumTasks = unfulfillPercent + processPercent + completePercent;
		//Nếu tổng lớn hơn 0 thì tính vì để phép chia được xác định khi mẫu khác 0
		if (sumTasks > 0) {
			unfulfillPercent = (unfulfillPercent * 100) / sumTasks;
			processPercent = (processPercent * 100) / sumTasks;
			completePercent = (completePercent * 100) / sumTasks;
		}
		percents.add(unfulfillPercent);
		percents.add(processPercent);
		percents.add(completePercent);
		return percents;
	}
	
	public void add(GroupworkDto dto) {
		// Chuyển dữ liệu từ DTO qua ENTITY
		Groupwork groupwork = new Groupwork();
		groupwork.setName(dto.getName());
		groupwork.setStartDate(dto.getStartDate());
		groupwork.setEndDate(dto.getEndDate());

		// Gọi hàm add của DAO để chạy câu lệnh INSERT
		groupworkDao.add(groupwork);
	}
	
	public void edit(GroupworkDto dto) {
		// Chuyển dữ liệu từ DTO qua ENTITY
		Groupwork groupwork = new Groupwork();
		groupwork.setId(dto.getId());
		groupwork.setName(dto.getName());
		groupwork.setStartDate(dto.getStartDate());
		groupwork.setEndDate(dto.getEndDate());
		// Gọi hàm add của DAO để chạy câu lệnh UPDATE
		groupworkDao.update(groupwork);
	}
	
	public void removeById(int id) {
		groupworkDao.deleteById(id);
	}
}
