package com.todoList.todoList.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todoList.todoList.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
	
	@Modifying
	@Query(
			"UPDATE User u SET u.firstName=:firstname, "
			+ "u.lastName=:lastname, "
			+ "u.country=:country where u.id = :id"
			)
	void updateUser(
			@Param(value = "id") Long id,
			@Param(value = "firstname") String firstName,
			@Param(value = "lastname") String lastName,
			@Param(value = "country") String country
			);
}
