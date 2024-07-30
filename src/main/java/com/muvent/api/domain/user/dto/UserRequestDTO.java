package com.muvent.api.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UserRequestDTO(

        @NotBlank(message = "The first name is required")
        String firstName,

        @NotBlank(message = "The last name is required")
        String lastName,

        @NotBlank(message = "The password is required")
        String password,

        @Email(message = "Invalid email format")
        @NotBlank(message = "The first name is required")
        String email,

        @CPF(message = "Invalid cpf")
        String cpf,

        @NotNull(message = "The birth date is required")
        LocalDate birthDate
) {
}
