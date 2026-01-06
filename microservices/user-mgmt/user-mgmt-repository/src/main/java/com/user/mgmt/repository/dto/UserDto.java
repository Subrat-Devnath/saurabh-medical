package com.user.mgmt.repository.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {

	private String name;

	private String email;

	private String city;

	private String country;

	private String password;

	private Boolean isActive;

	private int retryCount;

	private LocalDateTime lastLoginDate;

}
