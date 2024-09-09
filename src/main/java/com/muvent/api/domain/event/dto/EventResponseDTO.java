package com.muvent.api.domain.event.dto;

import com.muvent.api.domain.address.dto.AddressResponseDTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EventResponseDTO  (
        UUID id,
        String title,
        String description,
        LocalDateTime initialDate,
        LocalDateTime finalDate,
        String imgUrl,
        String eventUrl,
        Boolean remote,
        AddressResponseDTO addressResponseDTO
) implements Serializable {
}
