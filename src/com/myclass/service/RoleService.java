package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dao.RoleDao;
import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;

public class RoleService {

	private RoleDao roleDao = null;

	public RoleService() {
		roleDao = new RoleDao();
	}

	public List<RoleDto> getAll() {
		List<RoleDto> dtos = new ArrayList<RoleDto>();

		List<Role> roles = roleDao.findAll();

		// Chuyển dữ liệu từ ENTITY qua DTO
		for (Role role : roles) {
			dtos.add(new RoleDto(role.getId(), role.getName(), role.getDescription()));
		}
		return dtos;
	}

	public RoleDto getById(int id) {
		RoleDto dto = new RoleDto();

		Role role = roleDao.findById(id);

		// Chuyển dữ liệu từ ENTITY qua DTO
		dto.setId(role.getId());
		dto.setName(role.getName());
		dto.setDesc(role.getDescription());

		return dto;
	}

	public void add(RoleDto dto) {
		// Chuyển dữ liệu từ DTO qua ENTITY
		Role role = new Role();
		role.setName(dto.getName());
		role.setDescription(dto.getDesc());

		// Gọi hàm add của DAO để chạy câu lệnh INSERT
		roleDao.add(role);
	}

	public void edit(RoleDto dto) {
		// Chuyển dữ liệu từ DTO qua ENTITY
		Role role = new Role();
		role.setId(dto.getId());
		role.setName(dto.getName());
		role.setDescription(dto.getDesc());

		// Gọi hàm add của DAO để chạy câu lệnh INSERT
		roleDao.update(role);
	}

	public void removeById(int id) {
		roleDao.deleteById(id);
	}
}
