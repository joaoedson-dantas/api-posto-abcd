INSERT INTO users (name, login, password_hash) VALUES ('Developer', 'dev', '123456');

INSERT INTO globalsettings (label, key, value) VALUES 
    ('gasoline price', 'gasoline-price', '5'),
    ('diesel oil price', 'diesel-oil-price', '6'),
    ('tax amount', 'tax-value', '13');


INSERT INTO fueltanks (name, liters, created_at) VALUES 
    ('GASOLIINA', 10000, NOW()),
    ('DIESEL', 10000, NOW());


INSERT INTO fuelpumps (name, fuel_tank_id) VALUES 
    ('BOMBA-GASOLINA-1', 1),
    ('BOMBA-GASOLINA-2', 1),
    ('BOMBA-DIESEL-1', 2),
    ('BOMBA-DIESEL-2', 2);