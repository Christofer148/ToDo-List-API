package com.todoList.todoList.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	Long id;
	String username;
	String firstName;
	String lastName;
	String country;
}
