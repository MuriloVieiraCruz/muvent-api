package com.muvent.api.domain.event.dto;

import com.muvent.api.domain.address.dto.AddressRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record EventRequestDTO(

        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "")
        Long initialDateTime,

        @NotNull(message = "")
        Long finalDateTime,

        MultipartFile image,

        String eventUrl,

        @NotNull(message = "The event must be remote or personally")
        Boolean remote,

        AddressRequestDTO addressRequestDTO


) {
}
