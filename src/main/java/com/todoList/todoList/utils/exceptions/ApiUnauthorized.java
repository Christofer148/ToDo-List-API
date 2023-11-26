package com.todoList.todoList.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Customized status exception 401
 * @author Christofer Alcaraz
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiUnauthorized extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiUnauthorized(String message) {
		super(message);
	}
}
