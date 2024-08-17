package com.muvent.api.domain.ticket.dto;

import com.muvent.api.domain.event.dto.EventResponseDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record TicketResponseDTO(
        UUID id,
        Long price,
        Long quantity,
        LocalDate date,
        EventResponseDTO eventResponseDTO
) implements Serializable {
}
