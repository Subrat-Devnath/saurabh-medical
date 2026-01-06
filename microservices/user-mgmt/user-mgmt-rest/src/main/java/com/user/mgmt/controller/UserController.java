package com.user.mgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.service.dtos.ResponseDTO;
import com.user.mgmt.repository.dto.LoginRequest;
import com.user.mgmt.repository.dto.UserDto;
import com.user.mgmt.service.UserService;

@RestController
@RequestMapping(path = "/api/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/{id}")
	public UserDto getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@PostMapping("/user")
	public boolean addUser(@RequestBody UserDto userDto) {
		userService.addUser(userDto);
		return true;
	}

	@PostMapping("/login")
	public ResponseDTO login(@RequestBody LoginRequest loginRequest) {
		return userService.login(loginRequest.getEmail(), loginRequest.getPassword());
	}

	@PostMapping("/user-details-update")
	public ResponseDTO updateUser(@RequestParam String emailId, @RequestParam String password) {
		return userService.updateUser(emailId, password);
	}
}
