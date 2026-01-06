package com.user.mgmt.service;

import com.common.service.dtos.ResponseDTO;
import com.user.mgmt.repository.dto.UserDto;

public interface UserService {

	void addUser(UserDto userDto);

	UserDto getUserById(Long id);

	ResponseDTO login(String emailId, String password);

	ResponseDTO updateUser(String emailId, String password);

}
