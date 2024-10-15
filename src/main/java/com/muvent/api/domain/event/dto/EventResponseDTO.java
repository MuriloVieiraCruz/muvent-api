package com.muvent.api.domain.event.dto;

import java.io.Serializable;
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
        String address
) implements Serializable {
}
