package com.todoList.todoList.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoList.todoList.models.Role;
import com.todoList.todoList.models.User;
import com.todoList.todoList.models.UserDTO;
import com.todoList.todoList.models.UserRequest;
import com.todoList.todoList.models.UserResponse;
import com.todoList.todoList.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	@Autowired
	private final UserRepository userRepository;
	
	@Transactional
	public UserResponse updateUser(UserRequest userRequest) {
		User user = User.builder()
				.id(userRequest.getId())
				.firstName(userRequest.getFirstName())
				.lastName(userRequest.getLastName())
				.country(userRequest.getCountry())
				.role(Role.USER)
				.build();
		
		userRepository.updateUser(
				user.getId(), 
				user.getFirstName(), 
				user.getLastName(), 
				user.getCountry());
	
		return new UserResponse("El usuario se registró satisfactoriamente");
	}
	
	public UserDTO getUser(Long id) {
		User user = userRepository.findById(id).orElse(null);
		
		if (user!=null) {
			UserDTO userDTO = UserDTO.builder()
					.id(user.getId())
					.username(user.getUsername())
					.firstName(user.getFirstName())
					.lastName(user.getLastName())
					.country(user.getCountry())
					.build();
			return userDTO;
		}
		
		return null;
	}
	
}
