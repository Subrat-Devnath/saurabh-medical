package com.security.repository.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Builder
@Data
@Entity(name = "refresh_token")
public class RefreshTokenEntity {

    @PrimaryKey
    @Column(name = "token_id")
    private UUID tokenId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "created_at", nullable = false)
    private Integer createdAt;

    @Column(name = "expired_at", nullable = false)
    private Integer expiresAt;

    @Column(name = "revoked", nullable = false)
    private boolean revoked;

    @Column(name = "replaced_token")
    private String replacedToken;

}
