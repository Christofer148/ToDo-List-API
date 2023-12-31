package com.todoList.todoList.utils.validator;

import org.springframework.stereotype.Service;

import com.todoList.todoList.models.RequestTask;
import com.todoList.todoList.utils.exceptions.ApiUnprocessableEntity;

/*
 * Validation's Interface of received data to create/update tasks
 * @author Christofer Alcaraz
 */
@Service
public interface TaskValidable {
	void validate(RequestTask task) throws ApiUnprocessableEntity;
}
