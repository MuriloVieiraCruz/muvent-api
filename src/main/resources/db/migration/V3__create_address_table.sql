CREATE TABLE IF NOT EXISTS tb_address (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    neighborhood VARCHAR(250) NOT NULL,
    city VARCHAR(150) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    patio VARCHAR(250) NOT NULL,
    zip_code VARCHAR(8) NOT NULL,
    number VARCHAR(10) NOT NULL,
    complement VARCHAR(300),
    event_id UUID,
    FOREIGN KEY (event_id) REFERENCES tb_event(id) ON DELETE CASCADE
)