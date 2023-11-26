package com.todoList.todoList.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Customized status exception 404
 * @author Christofer Alcaraz
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiNotFound(String message) {
		super(message);
	}
}
