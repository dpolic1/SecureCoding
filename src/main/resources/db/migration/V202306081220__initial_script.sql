CREATE TABLE users (
    id bigserial PRIMARY KEY,
    username varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL
);

CREATE TABLE roles (
    id bigserial PRIMARY KEY ,
    role varchar(255) NOT NULL
);

CREATE TABLE user_roles (
    user_id bigint NOT NULL,
    user_role_id bigint NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_user_role_id FOREIGN KEY (user_role_id) REFERENCES roles (id)
);