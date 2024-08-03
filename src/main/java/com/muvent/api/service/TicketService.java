package com.muvent.api.service;

import com.muvent.api.domain.ticket.Ticket;
import com.muvent.api.domain.ticket.dto.TicketRequestDTO;
import com.muvent.api.domain.ticket.dto.TicketResponseDTO;
import com.muvent.api.domain.user.dto.UserResponseDTO;
import com.muvent.api.mapper.TicketMapper;
import com.muvent.api.repository.TicketRepository;
import com.muvent.api.strategy.ticketStrategies.UpdateTicketFields;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UpdateTicketFields updateTicketFields;

    public void createTicket(TicketRequestDTO ticketRequestDto) {
        ticketRepository.save(TicketMapper.toTicket(ticketRequestDto));
    }

    public TicketResponseDTO findTicket(UUID ticketId) {
        return TicketMapper.toTicketResponse(findTicketById(ticketId));
    }

    public TicketResponseDTO updateTicket(UUID ticketId, TicketRequestDTO ticketRequestDto) {
        Ticket ticket = findTicketById(ticketId);
        updateTicketFields.validate(ticket,ticketRequestDto);
        return TicketMapper.toTicketResponse(ticket);
    }

    public void deleteTicket(UUID ticketId) {

    }

    private Ticket findTicketById(UUID ticketId) {
        return ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Test"));
    }
}
