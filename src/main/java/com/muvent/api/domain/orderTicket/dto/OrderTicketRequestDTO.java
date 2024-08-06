package com.muvent.api.domain.orderTicket.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record OrderTicketRequestDTO(
        LocalDate purchaseDate,

        @NotNull(message = "The quantity is required")
        Long quantity,

        @NotNull(message = "The user id is required")
        UUID userId,

        @NotNull(message = "The ticket id is required")
        UUID ticketId,
        UUID couponId
) {
}
