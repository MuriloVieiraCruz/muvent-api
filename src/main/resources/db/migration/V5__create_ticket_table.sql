CREATE TABLE IF NOT EXISTS tb_ticket (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    price BIGINT NOT NULL,
    quantity BIGINT NOT NULL,
    date TIMESTAMP NOT NULL,
    active BOOLEAN DEFAULT true,
    event_id UUID NOT NULL,
    FOREIGN KEY (event_id) REFERENCES tb_event(id)
);

CREATE TABLE IF NOT EXISTS tb_order_ticket (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    total_amount BIGINT,
    quantity BIGINT NOT NULL,
    active BOOLEAN DEFAULT true,
    user_id UUID NOT NULL,
    ticket_id UUID NOT NULL,
    FOREIGN KEY (user_id) REFERENCES tb_user(id),
    FOREIGN KEY (ticket_id) REFERENCES tb_ticket(id)
);