package com.todoList.todoList.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Customized status exception 400
 * @author Christofer Alcaraz
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiBadRequest extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ApiBadRequest(String message) {
		super(message);
	}
}
