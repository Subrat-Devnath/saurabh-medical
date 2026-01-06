package com.user.mgmt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.common.service.configuration.ObjectBuilderUtils;
import com.common.service.dtos.ResponseDTO;
import com.user.mgmt.repository.UserRepository;
import com.user.mgmt.repository.dto.UserDto;
import com.user.mgmt.repository.entity.UserEntity;
import com.user.mgmt.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDto getUserById(Long id) {
		return userRepository.getUserById(id);
	}

	@Override
	public void addUser(UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		UserEntity userEntity = ObjectBuilderUtils.buildDtoToEntity(userDto, UserEntity.class);
		userRepository.addUser(userEntity);
	}

	@Override
	public ResponseDTO updateUser(String emailId, String password) {
		UserDto userByEmail = userRepository.getUserByEmail(emailId.trim());

		if (userByEmail == null) {
			return new ResponseDTO(false, null, "User not Found");
		}

		boolean updated = userRepository.updatePassword(emailId, password);
		return new ResponseDTO(updated, null, null);
	}

	@Override
	public ResponseDTO login(String emailId, String password) {

		UserDto userByEmail = userRepository.getUserByEmail(emailId);

		if (userByEmail == null) {
			return new ResponseDTO(false, null, "User not found");

		}

		// THIS IS THE CORRECT WAY
		boolean matches = passwordEncoder.matches(password, userByEmail.getPassword());

		if (matches) {
			return new ResponseDTO(true, null, null);
		}

		return new ResponseDTO(false, null, "Invalid email or password");
	}
}
