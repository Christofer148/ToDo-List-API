package com.todoList.todoList.utils.validator;

import org.springframework.stereotype.Service;

import com.todoList.todoList.utils.exceptions.ApiBadRequest;

/*
 * Validation's Interface of received id data
 * @author Christofer Alcaraz
 */
@Service
public interface IdValidable {
	Long validate(String id) throws ApiBadRequest;
}
