package com.muvent.api.mapper;

import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.ticket.Ticket;
import com.muvent.api.domain.ticket.dto.TicketRequestDTO;
import com.muvent.api.domain.ticket.dto.TicketResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public static Ticket toTicket(TicketRequestDTO dto) {
        return Ticket.builder()
                .price(dto.price())
                .quantity(dto.quantity())
                .date(dto.date())
                .event(new Event())
                .build();
    }

    public static TicketResponseDTO toTicketResponse(Ticket ticket) {
        return new TicketResponseDTO(
                ticket.getPrice(),
                ticket.getQuantity(),
                ticket.getDate(),
                EventMapper.toEventResponse(ticket.getEvent())
        );
    }
}
