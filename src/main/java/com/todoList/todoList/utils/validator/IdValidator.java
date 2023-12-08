package com.todoList.todoList.utils.validator;

import org.springframework.stereotype.Component;

import com.todoList.todoList.utils.exceptions.ApiBadRequest;

@Component
public class IdValidator implements IdValidable{

	@Override
	public Long validate(String idToValidate) throws ApiBadRequest {
		Long id = null;
		try {
			id = Long.valueOf(idToValidate);
		}catch(Exception e) {
			this.message("Invalid Id");
		}
		return id;
	}
	
	private void message(String message) throws ApiBadRequest{
		throw new ApiBadRequest(message);
	}
}
