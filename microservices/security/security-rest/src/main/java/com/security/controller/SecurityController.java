package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.service.dtos.LoginRequest;
import com.security.service.SecurityService;

@RestController
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecurityController {

	@Autowired
	private SecurityService securityService;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody LoginRequest loginRequest) {
		return securityService.loginUser(loginRequest);
	}

	@GetMapping("/test")
	public String test() {
		return "pass";
	}
}
