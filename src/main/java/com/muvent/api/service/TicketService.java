package com.muvent.api.service;

import com.muvent.api.domain.event.Event;
import com.muvent.api.domain.orderTicket.dto.OrderTicketRequestDTO;
import com.muvent.api.domain.orderTicket.dto.OrderTicketResponseDTO;
import com.muvent.api.domain.ticket.Ticket;
import com.muvent.api.domain.ticket.dto.TicketRequestDTO;
import com.muvent.api.domain.ticket.dto.TicketResponseDTO;
import com.muvent.api.domain.user.User;
import com.muvent.api.mapper.TicketMapper;
import com.muvent.api.repository.TicketRepository;
import com.muvent.api.strategy.ticketStrategies.UpdateTicketFields;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserService userService;
    private final OrderTicketService orderTicketService;
    private final UpdateTicketFields updateTicketFields;
    private final EventService eventService;

    @Transactional
    public TicketResponseDTO createTicket(TicketRequestDTO ticketRequestDto) {

        Event eventFound = eventService.findEventById(ticketRequestDto.eventId());

        Ticket ticket = TicketMapper.toTicket(ticketRequestDto);
        ticket.setEvent(eventFound);
        return TicketMapper.toTicketResponse(ticketRepository.save(ticket));
    }

    @Cacheable(value = "tickets", key = "#ticketId")
    public TicketResponseDTO findTicket(UUID ticketId) {
        return TicketMapper.toTicketResponse(findTicketById(ticketId));
    }

    @Transactional
    @CachePut(value = "tickets", key = "#ticketId")
    public TicketResponseDTO updateTicket(UUID ticketId, TicketRequestDTO ticketRequestDto) {
        Ticket ticket = findTicketById(ticketId);
        updateTicketFields.validate(ticket,ticketRequestDto);
        return TicketMapper.toTicketResponse(ticket);
    }

    @Transactional
    @CacheEvict(value = "tickets", key = "#ticketId", beforeInvocation = true)
    public void deleteTicket(UUID ticketId) {
        Ticket ticketFound = findTicketById(ticketId);
        ticketFound.setActive(false);
        ticketRepository.save(ticketFound);
    }

    private Ticket findTicketById(UUID ticketId) {
        return ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("Test"));
    }

    @Transactional
    public OrderTicketResponseDTO createOrder(OrderTicketRequestDTO orderTicketRequestDTO) {
        Ticket ticketFound = findTicketById(orderTicketRequestDTO.ticketId());

        if (orderTicketRequestDTO.quantity() > ticketFound.getQuantity()) {
            throw new RuntimeException("Test error quantity");
        }

        ticketFound.setQuantity(ticketFound.getQuantity() - orderTicketRequestDTO.quantity());
        User userFound = userService.findUserById(orderTicketRequestDTO.userId());

        return orderTicketService.createTicketOrder(ticketFound, userFound, orderTicketRequestDTO);
    }

    @Cacheable(value = "orders", key = "#orderId")
    public OrderTicketResponseDTO findOrderTicket(UUID orderId) {
        return orderTicketService.findOrderTicket(orderId);
    }

    @Transactional
    @CacheEvict(value = "orders", key = "#orderId", beforeInvocation = true)
    public void deleteOrderTicket(UUID orderId) {
        orderTicketService.deleteOrderTicket(orderId);
    }
}
