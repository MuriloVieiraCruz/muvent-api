package com.muvent.api.domain.user.dto;

import java.time.LocalDate;

public record UserResponseDTO(

        String firstName,
        String lastName,
        String email,
        String cpf,
        LocalDate birthDate
) {
}
