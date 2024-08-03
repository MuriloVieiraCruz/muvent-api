package com.muvent.api.controller;

import com.muvent.api.domain.ticket.dto.TicketRequestDTO;
import com.muvent.api.domain.ticket.dto.TicketResponseDTO;
import com.muvent.api.domain.user.dto.UserResponseDTO;
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
    public ResponseEntity<Void> createTicket(@RequestBody @Valid TicketRequestDTO ticketRequestDto) {
        ticketService.createTicket(ticketRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
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
}
