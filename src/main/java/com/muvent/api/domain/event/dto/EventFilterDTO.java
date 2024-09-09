package com.muvent.api.domain.event.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EventFilterDTO(
        int page,
        int size,
        String title,
        String city,
        String uf,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime endDate
) {
}
