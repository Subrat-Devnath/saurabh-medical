package com.security.config.service.impl;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.SecretKey;


import com.user.mgmt.client.dtos.RolesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.security.config.service.JwtService;
import com.user.mgmt.client.dtos.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.util.StringUtils;

@Service
public class JwtServiceImpl implements JwtService {

    private final SecretKey secretKey;
    private final long accessTtlSeconds;
    private final long refreshTtlSeconds;
    private final String issuer;

    public JwtServiceImpl(@Value("${security.jwt.secret}") String secret,

                          @Value("${security.jwt.access-ttl-seconds}") long accessTtlSeconds,

                          @Value("${security.jwt.refresh-ttl-seconds}") long refreshTtlSeconds,

                          @Value("${security.jwt.issuer}") String issuer) {

        if (StringUtils.isEmpty(secret) || secret.length() < 64) {
            throw new
                    IllegalArgumentException("Invalid key");
        }
        this.secretKey =
                Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        this.accessTtlSeconds = accessTtlSeconds;
        this.refreshTtlSeconds =
                refreshTtlSeconds;
        this.issuer = issuer;

    }

    @Override
    public String generateAccessToken(UserDto userDto) {

        Instant now = Instant.now();

        return Jwts.builder().id(UUID.randomUUID().toString()).subject(userDto.getName().toString()).issuer(issuer)
                .expiration(Date.from(now.plusSeconds(accessTtlSeconds)))
                .claims(

                        getStringObjectMap(userDto, true)

                )
                .signWith(secretKey, SignatureAlgorithm.HS512).compact();
    }

    private static Map<String, Object> getStringObjectMap(UserDto userDto, boolean isAccessToken) {
        Map<String, Object> keyAndValue = new HashMap<>();
        keyAndValue.put("emailId", userDto.getEmailId());
        keyAndValue.put("roles", userDto.getRoles().stream().map(RolesDTO::getName).toList());
        keyAndValue.put("tokenType", "refresh");
        if (isAccessToken) {
            keyAndValue.put("tokenType", "access");
        }

        if (userDto.getOrganization() != null) {
            keyAndValue.put("orgProfile", userDto.getOrganization().getOrgProfile());
            keyAndValue.put("orgId", userDto.getOrganization().getId());
        }
        return keyAndValue;
    }

    @Override
    public String generateRefereshToken(UserDto userDto, String jwtId) {

        Instant now = Instant.now();

        return Jwts.builder().id(jwtId).subject(userDto.getName().toString()).issuer(issuer)
                .expiration(Date.from(now.plusSeconds(refreshTtlSeconds))).claims(
                        getStringObjectMap(userDto, false)
                )
                .signWith(secretKey, SignatureAlgorithm.HS512).compact();
    }

    @Override
    public Jws<Claims> parse(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
    }

    @Override
    public boolean isAccessToken(String token) {
        Claims payload = parse(token).getPayload();
        return "access".equals(payload.get("tokenType"));
    }

    @Override
    public boolean isRefreshToken(String token) {
        Claims payload = parse(token).getPayload();
        return "refresh".equals(payload.get("tokenType"));
    }

    @Override
    public UUID getUserIdFromToken(String token) {
        Claims payload = parse(token).getPayload();
        return UUID.fromString(payload.getSubject());
    }

    @Override
    public String getTokenId(String token) {
        return parse(token).getPayload().getId();
    }

}
