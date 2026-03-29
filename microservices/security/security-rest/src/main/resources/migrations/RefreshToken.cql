CREATE TABLE refresh_token
(
    token_id       uuid,
    user_id        text,
    created_at     bigint,
    expired_at     bigint,
    revoked        boolean,
    replaced_token text,
    PRIMARY KEY ((token_id))
);