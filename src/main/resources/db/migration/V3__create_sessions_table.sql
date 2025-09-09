CREATE
EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE Sessions
(
    id       UUID PRIMARY KEY,
    user_id    INT       NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);