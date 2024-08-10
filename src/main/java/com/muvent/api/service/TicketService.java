package com.muvent.api.service;

import com.muvent.api.domain.orderTicket.dto.OrderTicketRequestDTO;
import com.muvent.api.domain.orderTicket.dto.OrderTicketResponseDTO;
import com.muvent.api.domain.ticket.Ticket;
import com.muvent.api.domain.ticket.dto.TicketRequestDTO;
import com.muvent.api.domain.ticket.dto.TicketResponseDTO;
import com.muvent.api.domain.user.User;
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
    private final UserService userService;
    private final OrderTicketService orderTicketService;
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
        Ticket ticketFound = findTicketById(ticketId);
        ticketFound.setActive(false);
        ticketRepository.save(ticketFound);
    }

    private Ticket findTicketById(UUID ticketId) {
        return ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Test"));
    }

    public void createOrder(OrderTicketRequestDTO orderTicketRequestDTO) {
        Ticket ticketFound = findTicketById(orderTicketRequestDTO.ticketId());
        ticketFound.setQuantity(ticketFound.getQuantity() - orderTicketRequestDTO.quantity());

        User userFound = userService.findUserById(orderTicketRequestDTO.userId());

        orderTicketService.createTicketOrder(ticketFound, userFound, orderTicketRequestDTO);
    }

    public OrderTicketResponseDTO findOrderTicket(UUID orderId) {
        return orderTicketService.findOrderTicket(orderId);
    }

    public void deleteOrderTicket(UUID orderId) {
        orderTicketService.deleteOrderTicket(orderId);
    }
}
