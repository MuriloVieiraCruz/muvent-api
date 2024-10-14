CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_event (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL,
    img_url VARCHAR(150) NULL,
    event_url VARCHAR(150) NOT NULL,
    remote BOOLEAN NOT NULL,
    final_date TIMESTAMP NOT NULL,
    initial_date TIMESTAMP NOT NULL,
    address VARCHAR(250),
    location geography(Point, 4326)
);

CREATE TABLE IF NOT EXISTS tb_coupon (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    code VARCHAR(100) NOT NULL,
    discount INTEGER NOT NULL,
    valid TIMESTAMP NOT NULL,
    event_id UUID NOT NULL,
    FOREIGN KEY (event_id) REFERENCES tb_event(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tb_user (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    birth_date TIMESTAMP NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    CONSTRAINT uc_email UNIQUE (email),
    CONSTRAINT uc_cpf UNIQUE (cpf)
);

CREATE TABLE IF NOT EXISTS tb_address (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    neighborhood VARCHAR(250) NOT NULL,
    city VARCHAR(150) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    patio VARCHAR(250) NOT NULL,
    zip_code VARCHAR(8) NOT NULL,
    number VARCHAR(10) NOT NULL,
    complement VARCHAR(300),
    user_id UUID,
    location geography(Point, 4326),
    FOREIGN KEY (user_id) REFERENCES tb_event(id) ON DELETE CASCADE
);
