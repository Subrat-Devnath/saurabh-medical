package com.user.mgmt.repository.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.user.mgmt.repository.entity.UserEntity;

import feign.Param;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByEmail(String email);

	@Query("UPDATE UserEntity u SET u.password = :password WHERE u.email = :email")
	int updatePasswordByEmail(@Param("email") String email, @Param("password") String password);
}
