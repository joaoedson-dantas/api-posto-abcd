CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    login VARCHAR(100) NOT NULL,
    password_hash VARCHAR(255) NOT NULL
);


CREATE TABLE globalSettings (
    id SERIAL PRIMARY KEY,
    label VARCHAR(100) NOT NULL,
    key VARCHAR(100) NOT NULL UNIQUE,
    value VARCHAR(255) NOT NULL
);

CREATE TABLE fuelTanks (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    liters BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE fuelPumps (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    fuel_tank_id BIGINT NOT NULL REFERENCES fuelTanks(id)
);

CREATE TABLE fillTanks (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    liters BIGINT NOT NULL,
    fuel_tank_id BIGINT NOT NULL REFERENCES fuelTanks(id)
);

CREATE TABLE supplies (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    liters BIGINT NOT NULL,
    price BIGINT NOT NULL,
    tax INT NOT NULL,
    fuel_pomp_id BIGINT NOT NULL REFERENCES fuelPumps(id)
);
