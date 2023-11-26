package com.todoList.todoList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoList.todoList.models.DataListedTask;
import com.todoList.todoList.models.DataResponseTask;
import com.todoList.todoList.models.RequestTask;
import com.todoList.todoList.models.Task;
import com.todoList.todoList.services.TaskService;
import com.todoList.todoList.utils.exceptions.ApiUnprocessableEntity;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/")
	public ResponseEntity<Page<DataListedTask>> getAll(@PageableDefault(size = 5) @Validated Pageable pagination) {
		return ResponseEntity.ok(taskService.getAllTask(pagination).map(DataListedTask::new));
	}
	
	@GetMapping("/completed")
	public ResponseEntity<Page<DataListedTask>> getAllCompleteTask(@PageableDefault(size = 5) @Validated Pageable pagination){
		return ResponseEntity.ok(taskService.findAllCompletedTask(pagination).map(DataListedTask::new));
	}
	
	@GetMapping("/incompleted")
	public ResponseEntity<Page<DataListedTask>> getAllIncompleteTask(@PageableDefault(size = 5) @Validated Pageable pagination){
		return ResponseEntity.ok(taskService.findAllIncompleteTask(pagination).map(DataListedTask::new));
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<DataResponseTask> getTaskById(@PathVariable @Validated Long id){
		Task task = taskService.findById(id);
		var dataTask = new DataResponseTask(task.getId(), task.getTitle(), task.getDescription(),
			task.getCompleted(), task.getDeadLine()
		);
		return ResponseEntity.ok(dataTask);
	}
	
	@PostMapping("/")
	public ResponseEntity<DataResponseTask> createNewTask(@RequestBody @Validated RequestTask requestTask) throws ApiUnprocessableEntity{
		Task task = taskService.createNewTask(requestTask);
		var dataTask = new DataResponseTask(task.getId(), task.getTitle(), task.getDescription(),
				task.getCompleted(), task.getDeadLine());
		return ResponseEntity.ok(dataTask);
	}
}
