package com.muvent.api.domain.event.dto;

import jakarta.validation.constraints.NotNull;

public record LocationDTO(

        @NotNull(message = "Latitude is required")
        double latitude,

        @NotNull(message = "Longitude is required")
        double longitude,

        double radius
) {
}
