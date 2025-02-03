CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
);

CREATE TABLE discounted_products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    discounted_price DECIMAL(10,2) NOT NULL,
    discount_percentage DECIMAL(5,2) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
