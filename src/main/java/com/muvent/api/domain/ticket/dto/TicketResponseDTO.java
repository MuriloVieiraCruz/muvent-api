package com.muvent.api.domain.ticket.dto;

import com.muvent.api.domain.event.dto.EventResponseDTO;

import java.time.LocalDate;

public record TicketResponseDTO(
        Long price,
        Long quantity,
        LocalDate date,
        EventResponseDTO eventResponseDTO
) {
}
