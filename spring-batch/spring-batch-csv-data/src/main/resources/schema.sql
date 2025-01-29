CREATE TABLE IF NOT EXISTS PRODUCTS (
    productId INT PRIMARY KEY,
    title VARCHAR(200),
    description VARCHAR(200),
    price DECIMAL(10,2),
    discount INT,
    discounted_price DECIMAL(10,2)
);
