package com.user.mgmt.client.dtos;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

@Data
public class UserDto implements Serializable {

    // fixed constant name to match serialization convention
    private static final long serialVersionUID = 1984390565274472623L;

    private String id;

    private String name;

    private String emailId;

    private String city;

    private String country;

    private String password;

    private String passwordSecret;

    private boolean isActive;

    private int retryCount;

    private Long lastLoginDate = System.currentTimeMillis();

    private OrganizationDTO organizationDTO;

    private Set<RolesDTO> roles;

}
