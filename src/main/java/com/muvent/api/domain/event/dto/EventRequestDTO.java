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

        @NotNull(message = "Initial date is required")
        Long initialDateTime,

        @NotNull(message = "Final date is required")
        Long finalDateTime,

        MultipartFile image,

        String eventUrl,

        @NotNull(message = "The event must be remote or personally")
        Boolean remote,

        AddressRequestDTO address
) {
}
