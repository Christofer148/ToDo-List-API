package com.todoList.todoList.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoList.todoList.models.UserDTO;
import com.todoList.todoList.models.UserRequest;
import com.todoList.todoList.models.UserResponse;
import com.todoList.todoList.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private final UserService userService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id){
		UserDTO userDTO = userService.getUser(id);
		if(userDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userDTO);
	}
	
	@PutMapping(value = "/")
	public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest){
		return ResponseEntity.ok(userService.updateUser(userRequest));
	}
}
