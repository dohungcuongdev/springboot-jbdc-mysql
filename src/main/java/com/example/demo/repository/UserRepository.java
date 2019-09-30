package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.User;

public interface UserRepository {

	void addUser(User user);
	List<User> getAllUsers();
	User getUserByUsername(String username);
	User getUserById(int id);
	void changeUser(int id, User user);
	void deleteUser(int id);
	String getFullNameById(int id);
	boolean isExist(int id);
}