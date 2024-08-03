package com.muvent.api.strategy.ticketStrategies.impl;

import com.muvent.api.domain.ticket.Ticket;
import com.muvent.api.domain.ticket.dto.TicketRequestDTO;
import com.muvent.api.service.EventService;
import com.muvent.api.strategy.ticketStrategies.UpdateTicketFields;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateTicketFieldsImpl implements UpdateTicketFields {

    private final EventService eventService;

    @Override
    public void validate(Ticket ticket, TicketRequestDTO ticketRequestDTO) {

        if (!ticket.getEvent().getId().equals(ticketRequestDTO.eventId())) {
            ticket.setEvent(eventService.findEventById(ticketRequestDTO.eventId()));
        }

        ticket.setDate(ticketRequestDTO.date());
        ticket.setPrice(ticketRequestDTO.price());
        ticket.setQuantity(ticketRequestDTO.quantity());
    }
}
