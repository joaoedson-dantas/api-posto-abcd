CREATE TABLE fuelPumps (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    fuel_tank_id BIGINT NOT NULL REFERENCES fuelTanks(id)
);
