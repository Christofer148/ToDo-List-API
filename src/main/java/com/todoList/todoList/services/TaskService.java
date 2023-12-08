package com.todoList.todoList.services;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.todoList.todoList.models.RequestTask;
import com.todoList.todoList.models.Task;
import com.todoList.todoList.repositories.TaskRepository;
import com.todoList.todoList.utils.exceptions.ApiNotFound;
import com.todoList.todoList.utils.validator.IdValidator;
import com.todoList.todoList.utils.validator.TaskValidator;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskValidator taskValidator;
	@Autowired
	private IdValidator idValidator;

	public Task createNewTask(RequestTask requestTask) {
		taskValidator.validate(requestTask);
		Task task = new Task();
		task.setTitle(requestTask.title());
		task.setDescription(requestTask.description());
		task.setDeadLine(requestTask.deadLine());
		task.setCompleted(false);
		task.setDeleted(false);
		task.setCreated(Date.valueOf(LocalDate.now()));
		return taskRepository.save(task);
	}
	
	public Page<Task> getAllTask(Pageable pagination){
		return taskRepository.findByDeletedIsFalse(pagination);
	}
	
	public Task findById(String idToValidate) {
		Long id = idValidator.validate(idToValidate);
		Task task = taskRepository.findById(id).orElseThrow(()-> new ApiNotFound("Item not found"));
		if(task.getDeleted()) new ApiNotFound("Item not found");
		return task;
	}
	
	public Page<Task> findAllCompletedTask(Pageable pagination){
		return taskRepository.findByCompletedIsTrue(pagination);
	}
	
	public Page<Task> findAllIncompleteTask(Pageable pagination){
		return taskRepository.findByCompletedIsFalse(pagination);
	}
	
	public Boolean deleteTaskById(Long id) {
		Task task = taskRepository.findById(id).orElseThrow(()-> new ApiNotFound("Item not found"));
		task.setDeleted(true);
		taskRepository.save(task);
		return true;
	}
	
	public Task updateTask(RequestTask requestTask) {
		Task task = taskRepository.findById(requestTask.id()).orElseThrow(()-> new ApiNotFound("Item not found"));
		task.setTitle(requestTask.title());
		task.setDescription(requestTask.description());
		task.setDeadLine(requestTask.deadLine());
		task.setCompleted(requestTask.completed());
		task.setLastModification(Date.valueOf(LocalDate.now()));
		taskValidator.validate(requestTask);
		return taskRepository.save(task);
	}
}
