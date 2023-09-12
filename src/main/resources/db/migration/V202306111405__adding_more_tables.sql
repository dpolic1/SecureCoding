CREATE TABLE order_status (
    id bigserial PRIMARY KEY,
    status_flag varchar(255) NOT NULL
);

CREATE TABLE orders (
    id bigserial PRIMARY KEY,
    order_name varchar(255) NOT NULL,
    order_date date NOT NULL,
    order_price decimal(10,2) NOT NULL,
    order_description varchar(255),
    order_status_id bigint NOT NULL,
    user_id bigint NOT NULL
);

ALTER TABLE orders
    ADD CONSTRAINT fk_order_status
    FOREIGN KEY (order_status_id) REFERENCES order_status(id),
    ADD CONSTRAINT fk_order_user
    FOREIGN KEY (user_id) REFERENCES users(id);