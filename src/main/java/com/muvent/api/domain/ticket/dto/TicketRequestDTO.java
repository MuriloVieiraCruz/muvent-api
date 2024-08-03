package com.muvent.api.domain.ticket.dto;

import java.time.LocalDate;
import java.util.UUID;

public record TicketRequestDTO(
        Long price,
        Long quantity,
        LocalDate date,
        UUID eventId
) {
}
