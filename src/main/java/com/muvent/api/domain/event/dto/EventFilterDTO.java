package com.muvent.api.domain.event.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record EventFilterDTO(
        int page,
        int size,
        String title,
        String city,
        String uf,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
) {
}
