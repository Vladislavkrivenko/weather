CREATE TABLE Locations
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(50)   NOT NULL,
    user_id    INT           NOT NULL,
    latitude  DECIMAL(9, 6) NOT NULL,
    longitude DECIMAL(9, 6) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);