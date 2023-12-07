CREATE TABLE supplyTanks (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    liters BIGINT NOT NULL,
    fuel_tank_id BIGINT NOT NULL REFERENCES fuelTanks(id)
);