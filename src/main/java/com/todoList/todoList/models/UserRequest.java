package com.todoList.todoList.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	Long id;
	String username;
	String firstName;
	String lastName;
	String country;
}
