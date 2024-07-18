package com.muvent.api.domain.event.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public record EventRequestDTO(
        String title,
        String description,
        Long date,
        MultipartFile image,
        String eventUrl,
        Boolean remote,
        String city,
        String uf
) {
}
