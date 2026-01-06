package com.user.mgmt.repository;

import com.user.mgmt.repository.dto.UserDto;
import com.user.mgmt.repository.entity.UserEntity;

public interface UserRepository {

	void addUser(UserEntity userEntity);

	UserDto getUserById(Long id);

	UserDto getUserByEmail(String email);

	boolean updatePassword(String emailId, String password);

}
