CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE order_items (
    id          UUID            NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    order_id    UUID            NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    product_id  UUID            NOT NULL REFERENCES products(id) ON DELETE RESTRICT,
    quantity    INTEGER         NOT NULL CHECK (quantity > 0),
    price       DECIMAL(10, 2) NOT NULL,
    created_at  TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP       NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_order_items_order_id ON order_items(order_id);
CREATE INDEX idx_order_items_product_id ON order_items(product_id);
