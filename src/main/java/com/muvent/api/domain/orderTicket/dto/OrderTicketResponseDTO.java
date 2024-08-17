package com.muvent.api.domain.orderTicket.dto;

import com.muvent.api.domain.ticket.dto.TicketResponseDTO;
import com.muvent.api.domain.user.dto.UserResponseDTO;

import java.io.Serializable;
import java.util.UUID;

public record OrderTicketResponseDTO(
        UUID id,
        Long totalAmount,
        Long quantity,
        UserResponseDTO userResponseDTO,
        TicketResponseDTO ticketResponseDTO
) implements Serializable {
}
