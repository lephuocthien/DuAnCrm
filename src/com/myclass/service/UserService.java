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
			// Láº¥y dá»¯ liá»‡u cá»§a role cĂ³ id trĂ¹ng vá»›i role_id cá»§a user
			Role role = roleDao.findById(user.getRoleId());
			
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setEmail(user.getEmail());
			dto.setPassword(user.getPassword());
			dto.setFullname(user.getFullname());
			dto.setAvatar(user.getAvatar());
			dto.setRoleId(user.getRoleId());
			// Láº¥y ra name cá»§a role gĂ¡n vĂ o roleName cá»§a userDto
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
		// B1. Kiá»ƒm tra email
		User user = userDao.findByEmail(email);
		// Email khĂ´ng Ä‘Ăºng
		if(user == null) return null;
		
		// B2. So sĂ¡nh máº­t kháº©u
		boolean checked = BCrypt.checkpw(password, user.getPassword());
		if(!checked) return null;
		
		// Gá»�i hĂ m findById cá»§a role 
		Role role = roleDao.findById(user.getRoleId());
		
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setRoleName(role.getName());
		
		return dto;
	}

	public UserDto getById(int id) {
		User user = userDao.findById(id);
		UserDto dto = new UserDto(
						user.getId(), 
						user.getEmail(), 
						user.getPassword(), 
						user.getFullname(),
						user.getAvatar(),
						user.getRoleId());
		
		return dto;
	}

	public void edit(UserDto dto) {
		String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12));
		User user = new User(
						dto.getId(),
						dto.getEmail(),
						hashed,
						dto.getFullname(),
						dto.getAvatar(),
						dto.getRoleId()
						);

		userDao.update(user);
	}

	public void deleteById(int id) {
		userDao.deleteById(id);
	}
}
