package com.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.common.service.dtos.LoginRequest;
import com.security.config.service.JwtService;
import com.security.service.SecurityService;
import com.user.mgmt.client.UserClient;
import com.user.mgmt.client.dtos.UserDto;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private UserClient userClient;

	@Autowired
	private JwtService jwtService;

	@Override
	public String loginUser(LoginRequest loginRequest) {

		if (loginRequest == null || StringUtils.isEmpty(loginRequest.getUserName())
				|| StringUtils.isEmpty(loginRequest.getPassword())) {
			return "Provide UserName and Password";
		}

		UserDto validateUserAndGet = userClient.validateUserAndGet(loginRequest);

		if (validateUserAndGet == null) {
			return "Invalid user and password";
		}

		return jwtService.generateAccessToken(validateUserAndGet);
	}

}
