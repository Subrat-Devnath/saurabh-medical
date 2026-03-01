package com.user.mgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.service.dtos.LoginRequest;
import com.user.mgmt.client.dtos.UserDto;
import com.user.mgmt.service.UserService;

@RestController
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/register-normal-user")
	public boolean addUser(@RequestBody UserDto userDto) {
		userService.addUser(userDto);
		return true;
	}

	@GetMapping(value = "/user/{id}")
	public UserDto getUserById(@PathVariable String id) {
		return userService.getUserById(id);
	}

	@GetMapping(value = "/{userName}")
	public UserDto getUserByUserName(@PathVariable String userName) {
		return userService.getUserByUserName(userName);
	}

	@PostMapping(value = "/validate/user")
	public UserDto validateUserAndGet(@RequestBody LoginRequest uerDetails) {
		return userService.validateUserAndGet(uerDetails);
	}

}
