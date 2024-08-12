package com.muvent.api.controller;

import com.muvent.api.domain.orderTicket.dto.OrderTicketRequestDTO;
import com.muvent.api.domain.orderTicket.dto.OrderTicketResponseDTO;
import com.muvent.api.domain.ticket.dto.TicketRequestDTO;
import com.muvent.api.domain.ticket.dto.TicketResponseDTO;
import com.muvent.api.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody @Valid TicketRequestDTO ticketRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createTicket(ticketRequestDto));
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> findTicket(@PathVariable UUID ticketId) {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.findTicket(ticketId));
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable UUID ticketId, @RequestBody @Valid TicketRequestDTO ticketRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.updateTicket(ticketId, ticketRequestDto));
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/order")
    public ResponseEntity<OrderTicketResponseDTO> createOrderTicket(@RequestBody @Valid OrderTicketRequestDTO orderTicketRequestDTO) {
        return  ResponseEntity.status(HttpStatus.OK).body(ticketService.createOrder(orderTicketRequestDTO));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderTicketResponseDTO> findOrderTicket(@PathVariable UUID orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.findOrderTicket(orderId));
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<Void> deleteOrderTicket(@PathVariable UUID orderId) {
        ticketService.deleteOrderTicket(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
