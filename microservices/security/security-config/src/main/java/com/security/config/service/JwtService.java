package com.security.config.service;

import java.util.UUID;

import com.user.mgmt.client.dtos.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface JwtService {

	String generateAccessToken(UserDto userDto);

	String generateRefereshToken(UserDto userDto, String jwtId);

	Jws<Claims> parse(String token);

	boolean isAccessToken(String token);

	boolean isRefreshToken(String token);

	UUID getUserIdFromToken(String token);

	String getTokenId(String token);
}
