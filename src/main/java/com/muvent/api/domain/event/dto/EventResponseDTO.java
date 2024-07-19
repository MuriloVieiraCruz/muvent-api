package com.muvent.api.domain.event.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.UUID;

public record EventResponseDTO(
        UUID id,
        String title,
        String description,
        LocalDate date,
        String imgUrl,
        String eventUrl,
        Boolean remote,
        String city,
        String uf
) {
}
