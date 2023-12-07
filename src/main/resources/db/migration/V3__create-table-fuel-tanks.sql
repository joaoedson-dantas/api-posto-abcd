CREATE TABLE fuelTanks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    liters BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL
);