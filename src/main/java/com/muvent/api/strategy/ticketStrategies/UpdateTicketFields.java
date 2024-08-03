package com.muvent.api.strategy.ticketStrategies;

import com.muvent.api.domain.ticket.Ticket;
import com.muvent.api.domain.ticket.dto.TicketRequestDTO;

public interface UpdateTicketFields {

    void validate(Ticket ticket, TicketRequestDTO ticketRequestDTO);
}
