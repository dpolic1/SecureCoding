CREATE TABLE articles (
    id bigserial PRIMARY KEY,
    article_code varchar(255) NOT NULL UNIQUE,
    article_name varchar(255) NOT NULL,
    article_price decimal(10,2) NOT NULL,
    article_description varchar(200) NOT NULL
);

CREATE TABLE orders_articles (
    id bigserial PRIMARY KEY,
    order_id bigint,
    article_id bigint,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (article_id) REFERENCES articles(id)
);