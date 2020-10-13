package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dao.RoleDao;
import com.myclass.dao.UserDao;
import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Role;
import com.myclass.entity.User;

public class UserService {
	
	private UserDao userDao = null;
	private RoleDao roleDao = null;
	
	public UserService() {
		userDao = new UserDao();
		roleDao = new RoleDao();
	}

	public List<UserDto> getAll() {
		List<UserDto> dtos = new ArrayList<UserDto>();
		
		List<User> users = userDao.findAll();
		for (User user : users) {
			// Lấy dữ liệu của role có id trùng với role_id của user
			Role role = roleDao.findById(user.getRoleId());
			
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setEmail(user.getEmail());
			dto.setPassword(user.getPassword());
			dto.setFullname(user.getFullname());
			dto.setAvatar(user.getAvatar());
			dto.setRoleId(user.getRoleId());
			// Lấy ra name của role gán vào roleName của userDto
			dto.setRoleName(role.getDescription());
			
			dtos.add(dto);
		}
		return dtos;
	}
	
	public List<UserDto> getAllDtos() {
		return userDao.findAllUserDtos();
	}
	
	public void save(UserDto dto) {
		
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12));
		
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setPassword(hashed);
		user.setFullname(dto.getFullname());
		user.setRoleId(dto.getRoleId());
		
		userDao.add(user);
	}
	
	public UserDto login(String email, String password) {
		// B1. Kiểm tra email
		User user = userDao.findByEmail(email);
		// Email không đúng
		if(user == null) return null;
		
		// B2. So sánh mật khẩu
		boolean checked = BCrypt.checkpw(password, user.getPassword());
		if(!checked) return null;
		
		// Gọi hàm findById của role 
		Role role = roleDao.findById(user.getRoleId());
		
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setRoleName(role.getName());
		
		return dto;
	}
}
