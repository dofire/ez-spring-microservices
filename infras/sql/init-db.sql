-- CREATE DATABASE keycloak;

-- user service
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (username, email, password)
VALUES ('admin', 'admin@example.com', '1');


-- orderEntity service
CREATE TABLE IF NOT EXISTS orders (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity INT NOT NULL CHECK (quantity > 0),
    total_price DECIMAL(10,2) NOT NULL CHECK (total_price >= 0),
    status VARCHAR(50) NOT NULL CHECK (status IN ('PENDING', 'CONFIRMED', 'CANCELED')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- product service
CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL CHECK (price >= 0),
    stock_quantity INT NOT NULL CHECK (stock_quantity >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO products (id, name, description, price, stock, created_at)
VALUES
    (gen_random_uuid(), 'Laptop', 'High-performance laptop', 1200.00, 10, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Smartphone', 'Latest model smartphone', 800.00, 25, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Headphones', 'Noise-canceling headphones', 150.00, 50, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Keyboard', 'Mechanical gaming keyboard', 100.00, 30, CURRENT_TIMESTAMP),
    (gen_random_uuid(), 'Mouse', 'Wireless ergonomic mouse', 50.00, 40, CURRENT_TIMESTAMP);
