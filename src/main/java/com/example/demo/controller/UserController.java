package com.example.demo.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ResponseAPI;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> getUserById(@PathVariable(value = "id", required = true) @Min(1) int id) throws Exception {
		return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> addNewUser(@RequestBody @Valid User user) throws Exception {
		userService.addUser(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> changeUser(@PathVariable(value = "id", required = true) @Min(1) int id, @RequestBody User user)
			throws Exception {
		userService.changeUser(id, user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
	public ResponseEntity<Object> deleteUser(@PathVariable(value = "id", required = true) @Min(1) int id)
			throws Exception {
		userService.deleteUser(id);
		return new ResponseEntity<>(new ResponseAPI("deleted successfully"), HttpStatus.OK);
	}
}