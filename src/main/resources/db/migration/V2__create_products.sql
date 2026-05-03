CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE products (
    id              UUID            NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    name            VARCHAR(255)    NOT NULL,
    price           DECIMAL(10,2)   NOT NULL,
    stock_quantity  INTEGER         NOT NULL DEFAULT 0,
    description     TEXT,
    category        VARCHAR(50)     NOT NULL,
    created_at      TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMP       NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_products_category ON products(category);