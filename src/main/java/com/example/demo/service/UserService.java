package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
	void addUser(User user) throws Exception;
	List<User> getAllUsers();
	User getUserByUsername(String username) throws Exception;
	User getUserById(int id) throws Exception;
	void changeUser(int id, User user) throws Exception;
	void deleteUser(int id) throws Exception;
}