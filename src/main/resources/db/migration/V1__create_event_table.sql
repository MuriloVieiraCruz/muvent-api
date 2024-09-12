CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_event (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(250) NOT NULL,
    img_url VARCHAR(150) NULL,
    event_url VARCHAR(150) NOT NULL,
    remote BOOLEAN NOT NULL,
    final_date TIMESTAMP NOT NULL,
    initial_date TIMESTAMP NOT NULL
);