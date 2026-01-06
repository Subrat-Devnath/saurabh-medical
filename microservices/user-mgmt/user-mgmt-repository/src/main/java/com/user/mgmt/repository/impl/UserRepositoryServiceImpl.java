package com.user.mgmt.repository.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.common.service.configuration.ObjectBuilderUtils;
import com.user.mgmt.repository.UserRepository;
import com.user.mgmt.repository.dao.UserDao;
import com.user.mgmt.repository.dto.UserDto;
import com.user.mgmt.repository.entity.UserEntity;

@Service
public class UserRepositoryServiceImpl implements UserRepository {

	@Autowired
	private UserDao userDao;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public UserDto getUserById(Long id) {
		Optional<UserEntity> userEntity = userDao.findById(id);
		boolean present = userEntity.isPresent();
		if (!present) {
			return null;
		}
		return ObjectBuilderUtils.buildDtoToEntity(userEntity, UserDto.class);
	}

	@Override
	public void addUser(UserEntity userEntity) {
		userEntity.setIsActive(true);
		userEntity.setLastLoginDate(LocalDateTime.now());
		userDao.save(userEntity);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		Optional<UserEntity> userEntity = userDao.findByEmail(email.trim());
		boolean present = userEntity.isPresent();
		if (!present) {
			return null;
		}
		return ObjectBuilderUtils.buildDtoToEntity(userEntity, UserDto.class);
	}

	@Override
	public boolean updatePassword(String email, String rawPassword) {

		String encryptedPassword = passwordEncoder.encode(rawPassword);

		int updatedRows = userDao.updatePasswordByEmail(email, encryptedPassword);

		return updatedRows > 0;
	}

}
