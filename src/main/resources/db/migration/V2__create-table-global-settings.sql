CREATE TABLE globalSettings (
    id SERIAL PRIMARY KEY,
    label VARCHAR(100) NOT NULL,
    key VARCHAR(100) NOT NULL UNIQUE,
    value VARCHAR(255) NOT NULL
);