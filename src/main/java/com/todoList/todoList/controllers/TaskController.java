package com.todoList.todoList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.todoList.todoList.models.DataListedTask;
import com.todoList.todoList.models.DataResponseTask;
import com.todoList.todoList.models.RequestTask;
import com.todoList.todoList.models.Task;
import com.todoList.todoList.services.TaskService;
import com.todoList.todoList.utils.exceptions.ApiUnprocessableEntity;

@CrossOrigin(origins = "http://localhost:4200")
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
	public ResponseEntity<DataResponseTask> getTaskById(@PathVariable("id") @Validated String id){
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
	
	@PutMapping("/")
	public ResponseEntity<DataResponseTask> updateTask(@RequestBody @Validated RequestTask requestTask) throws ApiUnprocessableEntity{
		Task task = taskService.updateTask(requestTask);
		var dataTask = new DataResponseTask(task.getId(), task.getTitle(), task.getDescription(),
				task.getCompleted(), task.getDeadLine()
			);
		return ResponseEntity.ok(dataTask);
	}
	
	@DeleteMapping("/")
	@ResponseBody
	public void updateTask(@RequestBody @Validated Task task) throws ApiUnprocessableEntity{
		if(taskService.deleteTaskById(task.getId())) new ResponseEntity<>("Task deleted successfully!", HttpStatus.OK);
	}
}
