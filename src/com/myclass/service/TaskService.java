package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import com.myclass.dao.GroupworkDao;
import com.myclass.dao.RoleDao;
import com.myclass.dao.TaskDao;
import com.myclass.dao.UserDao;
import com.myclass.dto.GroupworkDto;
import com.myclass.dto.StatusDto;
import com.myclass.dto.TaskDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Groupwork;
import com.myclass.entity.Role;
import com.myclass.entity.Status;
import com.myclass.entity.Task;
import com.myclass.entity.User;

public class TaskService {
	private UserDao userDao = null;
	private TaskDao taskDao = null;
	private GroupworkDao groupworkDao = null;

	public TaskService() {
		userDao = new UserDao();
		taskDao = new TaskDao();
		groupworkDao = new GroupworkDao();
	}

	public List<StatusDto> getAllStatus() {
		List<StatusDto> dtos = new ArrayList<StatusDto>();
		List<Status> statuss = taskDao.findAllStatus();
		// Chuyển dữ liệu từ ENTITY qua DTO
		for (Status status : statuss) {
			StatusDto dto = new StatusDto();
			dto.setId(status.getId());
			dto.setName(status.getName());
			dtos.add(dto);
		}
		return dtos;
	}

	public StatusDto getStatusById(int id) {
		StatusDto dto = new StatusDto();
		Status status = taskDao.findStatusById(id);
		// Chuyển dữ liệu từ ENTITY qua DTO
		dto.setId(status.getId());
		dto.setName(status.getName());
		return dto;
	}

	public List<TaskDto> getAllDtosByManager(int id) {
		List<TaskDto> fullTasks = null;//Tất cả công việc
		List<TaskDto> tasks = null;//Công việc của manager và công việc manager quản lý
		List<TaskDto> taskOfManager = new ArrayList<TaskDto>();//Chỉ công việc manager quản lý
		fullTasks = taskDao.findAllDtos();
		tasks = taskDao.findAllDtosByUser(id);//Hiện tại chỉ có công việc của manager
		
		for (int i = 0; i < fullTasks.size(); i++) {
			boolean check = false;// Biến check kiểm tra sự tồn tại của công việc trong tasks và fulltask có trùng nhau hay ko
			for (int j = 0; j < tasks.size(); j++) {
				// nếu task id trong task  trùng với fulltasks và cùng một dự án thì check = false vad break;	
				if (fullTasks.get(i).getId() == tasks.get(j).getId()
						&& fullTasks.get(i).getGroupworkName().equals(tasks.get(j).getGroupworkName())) {
					check = false;
					System.out.println(tasks.get(j).getId());
					System.out.println(tasks.get(j).getName());
					System.out.println(fullTasks.get(i).getId());
					System.out.println(fullTasks.get(i).getName());
					System.out.println(check);
					System.out.println();
					break;
				} 
				// Nếu sai và và cùng một dự án thì check = true
				else if(fullTasks.get(i).getGroupworkName().equals(tasks.get(j).getGroupworkName())) {
					check = true;//Ngược lại thì flase
				}
			}
			// nếu task id trong task không trùng với fulltasks và cùng một dự án thì thêm công việc vào tasks và taskOfManager
			if (check == true) {
				tasks.add(fullTasks.get(i));//Thêm công việc manager quản lý vào để kiểm tra trùng lặp trong vòng lặp
				taskOfManager.add(fullTasks.get(i));
			}
		}
		return taskOfManager;
	}
	
	public List<TaskDto> getAllDtosByUser(int id) {
		return taskDao.findAllDtosByUser(id);
	}

	public List<TaskDto> getAll() {
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		List<Task> tasks = taskDao.findAll();
		for (Task task : tasks) {
			// Lấy dữ liệu của user, groupwork, status có id trùng với user_id, groupwork_id, status_id của task
			User user = userDao.findById(task.getUserId());
			Groupwork groupwork = groupworkDao.findById(task.getGroupworkId());
			Status status = taskDao.findStatusById(task.getStatusId());

			TaskDto dto = new TaskDto();
			// Chuyển dữ liệu từ ENTITY qua DTO
			dto.setId(task.getId());
			dto.setName(task.getName());
			dto.setStartDate(task.getStartDate());
			dto.setEndDate(task.getEndDate());
			dto.setUserId(task.getUserId());
			dto.setGroupworkId(task.getGroupworkId());
			dto.setStatusId(task.getStatusId());
			dto.setUserName(user.getFullname());
			dto.setGroupworkName(groupwork.getName());
			dto.setStatusName(status.getName());

			dtos.add(dto);
		}
		return dtos;
	}

	public TaskDto getById(int id) {
		Task task = taskDao.findById(id);
		// Lấy dữ liệu của user, groupwork, status có id trùng với user_id, groupwork_id, status_id của task
		User user = userDao.findById(task.getUserId());
		Groupwork groupwork = groupworkDao.findById(task.getGroupworkId());
		Status status = taskDao.findStatusById(task.getStatusId());
		// Chuyển dữ liệu từ ENTITY qua DTO
		TaskDto dto = new TaskDto();
		dto.setId(task.getId());
		dto.setName(task.getName());
		dto.setStartDate(task.getStartDate());
		dto.setEndDate(task.getEndDate());
		dto.setUserId(task.getUserId());
		dto.setGroupworkId(task.getGroupworkId());
		dto.setStatusId(task.getStatusId());
		dto.setUserName(user.getFullname());
		dto.setGroupworkName(groupwork.getName());
		dto.setStatusName(status.getName());
		return dto;
	}

	public List<TaskDto> getAllDtos() {
		return taskDao.findAllDtos();
	}

	public TaskDto getDtoById(int id) {
		return taskDao.findDtosById(id);
	}

	//Phương thức tính % trạng thái công việc của user
	public List<Float> getAllPercent(int id) {
		float completePercent = 0;//Đã hoàn thành
		float processPercent = 0;//Đang thực hiện
		float unfulfillPercent = 0;//Chưa hoàn thành
		float sumTasks = 0;//Tổng các công việc của user
		List<Float> percents = new ArrayList<Float>();//Lưu % trạng thái công việc của user
		List<TaskDto> tasks = taskDao.findAllDtosByUser(id);// Tất cả các công việc của user
		for (TaskDto task : tasks) {
			if (task.getStatusName().equals("Chưa thực hiện")) {
				unfulfillPercent++;
			} else if (task.getStatusName().equals("Đang thực hiện")) {
				processPercent++;
			} else if (task.getStatusName().equals("Đã hoàn thành")) {
				completePercent++;
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

	public void add(TaskDto dto) {
		// Chuyển dữ liệu từ DTO qua ENTITY
		Task task = new Task();
		task.setName(dto.getName());
		task.setStartDate(dto.getStartDate());
		task.setEndDate(dto.getEndDate());
		task.setUserId(dto.getUserId());
		task.setGroupworkId(dto.getGroupworkId());
		// Gọi hàm add của DAO để chạy câu lệnh UPDATE
		taskDao.add(task);
	}

	public void edit(TaskDto dto) {

		// Chuyển dữ liệu từ DTO qua ENTITY
		Task task = new Task();
		task.setId(dto.getId());
		task.setName(dto.getName());
		task.setStartDate(dto.getStartDate());
		task.setEndDate(dto.getEndDate());
		task.setUserId(dto.getUserId());
		task.setGroupworkId(dto.getGroupworkId());
		// Gọi hàm update của DAO để chạy câu lệnh UPDATE
		taskDao.update(task);

	}

	public void editStatus(TaskDto dto) {
		// Chuyển dữ liệu từ DTO qua ENTITY
		Task task = new Task();
		task.setId(dto.getId());
		task.setStatusId(dto.getStatusId());
		// Gọi hàm update của DAO để chạy câu lệnh UPDATE
		taskDao.updateStatus(task);
	}

	public void removeById(int id) {
		taskDao.deleteById(id);
	}
}
