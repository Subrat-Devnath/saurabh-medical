package com.user.mgmt.repository.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -5649645038689214691L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "retry_count")
	private int retryCount;

	@Column(name = "last_login_date")
	private LocalDateTime lastLoginDate;
}
