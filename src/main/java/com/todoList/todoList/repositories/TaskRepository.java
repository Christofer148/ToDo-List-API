package com.todoList.todoList.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todoList.todoList.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	//@Query(value = "SELECT t FROM tb_tasks t WHERE t.title := title")
	//public Task findByTitle(@Param(value="title") String title);
	
	Page<Task> findByCompletedIsTrue(Pageable pageable);
	
	public Page<Task> findByCompletedIsFalse(Pageable pageable);
	
	public Page<Task> findAll(Pageable pageable);
}
