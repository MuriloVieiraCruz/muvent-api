package com.muvent.api.domain.event.dto;

import java.util.Date;
import java.util.UUID;

public record EventResponseDTO(
        UUID id,
        String title,
        String description,
        Date date,
        String imgUrl,
        String eventUrl,
        Boolean remote
) {
}
