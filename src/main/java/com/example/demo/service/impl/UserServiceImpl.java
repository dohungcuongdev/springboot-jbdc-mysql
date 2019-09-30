package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void addUser(User user) throws Exception {
		if(userRepository.isExist(user.getId())) {
			throw new Exception("user is already exist");
		}
		userRepository.addUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public User getUserByUsername(String username) throws Exception {
		return userRepository.getUserByUsername(username);
	}
	
	@Override
	public User getUserById(int id) throws Exception {
		return userRepository.getUserById(id);
	}

	@Override
	public void changeUser(int id, User user) throws Exception {
		if(id != user.getId()) {
			throw new Exception("incorrect id");
		}
		if(userRepository.getUserById(id) == null) {
			throw new Exception("user is not available");
		}
		userRepository.changeUser(id, user);
	}

	@Override
	public void deleteUser(int id) throws Exception {
		if(userRepository.getUserById(id) == null) {
			throw new Exception("user is not available");
		}
		userRepository.deleteUser(id);
	}
}
