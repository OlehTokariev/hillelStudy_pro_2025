CREATE TABLE IF NOT EXISTS customer (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    social_security_number VARCHAR(20) NOT NULL UNIQUE
);

SELECT * FROM customer;
