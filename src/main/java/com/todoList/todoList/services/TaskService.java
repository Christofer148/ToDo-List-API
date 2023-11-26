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
import com.todoList.todoList.utils.validator.TaskValidatorImplement;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskValidatorImplement taskValidator;

	public Task createNewTask(RequestTask requestTask) {
		taskValidator.validate(requestTask);
		Task task = new Task();
		task.setTitle(requestTask.title());
		task.setDescription(requestTask.description());
		task.setDeadLine(requestTask.deadLine());
		task.setCompleted(false);
		task.setCreated(Date.valueOf(LocalDate.now()));
		return taskRepository.save(task);
	}
	
	public Page<Task> getAllTask(Pageable pagination){
		return taskRepository.findAll(pagination);
	}
	
	public Task findById(Long id) {
		Task task = taskRepository.findById(id).orElseThrow(()-> new ApiNotFound("Item not found"));
		return task;
	}
	
	public Page<Task> findAllCompletedTask(Pageable pagination){
		return taskRepository.findByCompletedIsTrue(pagination);
	}
	
	public Page<Task> findAllIncompleteTask(Pageable pagination){
		return taskRepository.findByCompletedIsFalse(pagination);
	}
	
	public void deleteTask(Task task) {
		taskRepository.delete(task);
	}
	
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}
}
