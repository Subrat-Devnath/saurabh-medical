package com.security.service;

import com.common.service.dtos.LoginRequest;

public interface SecurityService {

	String loginUser(LoginRequest userDetails);

}
