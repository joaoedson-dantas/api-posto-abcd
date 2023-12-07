CREATE TABLE supplies (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    liters BIGINT NOT NULL,
    price BIGINT NOT NULL,
    tax INT NOT NULL,
    fuel_pomp_id BIGINT NOT NULL REFERENCES fuelPumps(id)
);
