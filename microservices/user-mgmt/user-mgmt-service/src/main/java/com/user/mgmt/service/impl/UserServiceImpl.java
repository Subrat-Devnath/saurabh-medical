package com.user.mgmt.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.UUID;

import com.security.client.dtos.SourceIdentity;
import com.security.config.service.impl.WebSecurityConfig;
import com.security.config.utils.SecurityUtil;
import com.user.mgmt.client.enums.RoleType;
import com.user.mgmt.repository.OrganizationRepository;
import com.user.mgmt.repository.RolesRepository;
import com.user.mgmt.repository.entity.OrganizationEntity;
import com.user.mgmt.repository.entity.RolesEntity;
import com.user.mgmt.repository.enums.OrgProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.common.service.configuration.ObjectBuilder;
import com.common.service.dtos.LoginRequest;
import com.user.mgmt.client.dtos.UserDTO;
import com.user.mgmt.repository.UserRepository;
import com.user.mgmt.repository.entity.UserEntity;
import com.user.mgmt.service.UserService;
import com.user.mgmt.service.utils.PasswordEncryptor;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Override
    public void addUser(UserDTO userDto) {

        try {
            String userSalt = UUID.randomUUID().toString();
            userDto.setPasswordSecret(userSalt);

            String encryptedPassword = webSecurityConfig.passwordEncoder().encode(userDto.getPassword());

            userDto.setPassword(encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserEntity userEntity = ObjectBuilder.buildDtoFromEntity(userDto, null, UserEntity.class);

        RolesEntity rolesEntity = rolesRepository
                .getRoleByName(RoleType.USER.name());

        if (rolesEntity == null) {
            return;
        }

        userEntity.setRoles(Set.of(rolesEntity));

        OrganizationEntity organizationEntity =
                organizationRepository.getOrganizationByName(
                        userEntity.getEmailId().split("@")[1]
                );

        if (organizationEntity != null) {

            userEntity.setOrganization(organizationEntity);
            userRepository.addUser(userEntity);
            return;
        }

        OrganizationEntity newOrganizationEntity = new OrganizationEntity();
        newOrganizationEntity.setId(UUID.randomUUID().toString());
        newOrganizationEntity.setName(userEntity.getEmailId().split("@")[1]);
        newOrganizationEntity.setCity(userEntity.getCity());
        newOrganizationEntity.setDeleted(false);
        newOrganizationEntity.setEnabled(true);
        newOrganizationEntity.setOrgProfile(OrgProfile.RESELLER);
        newOrganizationEntity.setTestOrg(false);

        organizationRepository.insertOrganization(newOrganizationEntity);

        userEntity.setOrganization(newOrganizationEntity);

        userRepository.addUser(userEntity);

    }

    @Override
    public UserDTO getUserById(String id) {
        UserEntity userById = userRepository.getUserById(id);
        if (userById == null) {
            return null;
        }
        return ObjectBuilder.buildDtoFromEntity(userById, null, UserDTO.class);
    }

    @Override
    public UserDTO getUserByUserName(String userName) {

        if (!StringUtils.hasText(userName)) {
            return null;
        }

        SourceIdentity principal = SecurityUtil.getPrincipal();

        //String orgId = principal.getOrgId();

        UserEntity userEntity = userRepository.getUserByUserName(userName);

        if (userEntity == null) {
            return null;
        }

        OrganizationEntity organizationEntity =
                organizationRepository.getOrganizationByName(
                        userEntity.getEmailId().split("@")[1]
                );

        userEntity.setOrganization(organizationEntity);

        return ObjectBuilder.buildDtoFromEntity(userEntity, null, UserDTO.class);
    }

    @Override
    public UserDTO validateUserAndGet(LoginRequest loginRequest) {

        UserDTO userByUserName = getUserByUserName(loginRequest.getUserName());

        if (userByUserName == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Decrypting the password and matching with the stored hash
        String hash = webSecurityConfig.passwordEncoder().encode(loginRequest.getPassword());

        // Matching the raw password with the stored hash
        boolean match = webSecurityConfig.passwordEncoder().matches(loginRequest.getPassword(), hash);

        if (match) {
            return userByUserName;
        }

        return null;
    }

}
