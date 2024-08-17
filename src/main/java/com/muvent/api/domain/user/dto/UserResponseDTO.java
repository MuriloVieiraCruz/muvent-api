package com.muvent.api.domain.user.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDTO(

        UUID id,
        String firstName,
        String lastName,
        String email,
        String cpf,
        LocalDate birthDate
) implements Serializable {
}
