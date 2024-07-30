CREATE TABLE IF NOT EXISTS tb_user (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    birthDate TIMESTAMP NOT NULL,
    active BOOLEAN DEFAULT true,
    CONSTRAINT uc_email UNIQUE (email),
    CONSTRAINT uc_cpf UNIQUE (cpf)
)